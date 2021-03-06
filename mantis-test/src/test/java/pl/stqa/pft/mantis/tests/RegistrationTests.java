package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

  @BeforeMethod(alwaysRun = true)
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws InterruptedException, IOException {
    String email = "user2@localhost.localdomain";
    String user1 = "user11";
    app.registration().start(user1, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 100000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String password = "password";
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user1, password));
    Thread.sleep(2000);
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
