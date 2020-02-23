package pl.stqa.pft.mantis.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

  @BeforeMethod(alwaysRun = true)
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void changePassword() {
    app.panel().login("administrator", "root");
    app.panel().getUser();
    String email = app.panel().getEmail();
    app.panel().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.panel().finish(confirmationLink);
    String username = app.panel().getUsername();
    app.panel().changePassword("12345678", "12345678");
    app.panel().login(username, "12345678");
    WebElement logoutButton = app.panel().logout();
    assertTrue(logoutButton.isDisplayed());

  }

  private String  findConfirmationLink(List<MailMessage> mailMessages, String email) {
    // znajdujemy tylko ten który przychopdzi na konkretny adres
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    // Liczba symboli która nie jest spacja nonSpace jeden lub wiecej oneOrMore
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //Stop serwera
  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
