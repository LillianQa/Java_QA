package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.newContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModification extends TestBase {


  @BeforeMethod()
  public void ensurePrecondition() {
    if (app.db().contact().size() == 0) {
      app.goTo().HomePage();
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2").withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), true);
      app.goTo().HomePage();
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
    Contacts before = app.db().contact();
    newContactData modifiedContact = before.iterator().next();
    newContactData contacts = new newContactData().withId(modifiedContact.getId()).withFirstname("Admin").withLastname("Admin2").withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987");
    Thread.sleep(3000);
    app.goTo().HomePage();
    app.contact().modify(contacts);
    Thread.sleep(3000);
    Contacts after = app.db().contact();
//    assertThat(app.contact().getContactCount(), equalTo(before.size()));

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contacts)));
  }
}