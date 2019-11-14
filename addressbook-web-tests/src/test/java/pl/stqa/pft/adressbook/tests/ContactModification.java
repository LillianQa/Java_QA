package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.HashSet;
import java.util.List;


public class ContactModification extends TestBase {


  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().getContact(new newContactData("Admin", "Admnin2", "Title", "Company",
              "Poland", "+48 678 876 987", "admin@onet.pl", "test1"), false);
    }
    List<newContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().editContact();
    newContactData contacts = new newContactData(before.get(before.size() -1).getId(), "Admin", "Admnin2", "Title", "Company",
            "Poland", "+48 678 876 987", "admin@onet.pl", "test1");
    app.getContactHelper().fillNewContactForm(contacts, false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();
    List<newContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() -1);
    before.add(contacts);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}