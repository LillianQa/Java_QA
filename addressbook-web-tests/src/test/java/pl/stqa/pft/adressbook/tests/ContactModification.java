package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().editContact();
    app.getContactHelper().fillNewContactForm(new newContactData("Admin", "Admnin2", "Title", "Company", "Poland", "+48 678 876 987", "admin@onet.pl", "test1"), false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();

  }
}