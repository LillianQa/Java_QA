package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.HashSet;
import java.util.List;


public class ContactModification extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2")
              .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), false);
    }
  }

  @Test(enabled = false)
  public void testContactModification() {
    List<newContactData> before = app.contact().list();
    int index = before.size() -1;
    newContactData contacts = new newContactData().withId(before.get(index).getId()).withFirstname("Admin").withLastname("Admin2")
            .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987");
    app.goTo().HomePage();
    app.contact().modify(index, contacts);
    List<newContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contacts);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}