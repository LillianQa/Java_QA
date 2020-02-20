package pl.stqa.pft.mantis.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

  @BeforeMethod(alwaysRun = true)
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void changePassword() throws IOException {
    app.panel().login("administrator", "root");
    String email = "user2@localhost.localdomain";
    app.panel().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.panel().finish(confirmationLink);
    app.panel().changePassword("1234567", "1234567");
    app.panel().login("user7", "1234567");
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
