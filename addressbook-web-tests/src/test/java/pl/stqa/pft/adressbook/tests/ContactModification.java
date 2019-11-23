package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.Set;


public class ContactModification extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2")
              .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), true);
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
    Thread.sleep(1000);
    Set<newContactData> before = app.contact().all();
    newContactData modifiedContact = before.iterator().next();
    newContactData contacts = new newContactData()
            .withId(modifiedContact.getId()).withFirstname("Admin").withLastname("Admin2")
            .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987");
    app.goTo().HomePage();
    app.contact().modify(contacts);
    Thread.sleep(1000);
    Set<newContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contacts);
    Assert.assertEquals(before, after);
  }
}