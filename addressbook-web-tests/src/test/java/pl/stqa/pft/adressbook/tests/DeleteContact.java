package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.newContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class DeleteContact extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().contact().size() == 0) {
      app.goTo().HomePage();
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2")
              .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), true);
      app.goTo().HomePage();
    }

  }

  @Test
  public void testDeleteContact() throws InterruptedException {
    Thread.sleep(1000);
    Contacts before = app.db().contact();
    newContactData deletedContact = before.iterator().next();
    app.goTo().HomePage();
    app.contact().delete(deletedContact);
    app.group().switchtoAlert();
    app.goTo().HomePage();
    Thread.sleep(1000);
    assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
    Contacts after = app.db().contact();

    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();

  }

}
