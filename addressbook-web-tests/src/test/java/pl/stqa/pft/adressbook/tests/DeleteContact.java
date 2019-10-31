package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;


public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() {

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().getContact(new newContactData("Admin", "Admnin2", "Title", "Company",
              "Poland", "+48 678 876 987", "admin@onet.pl", "test1"), true);
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteContact();
    app.getGroupHelper().switchtoAlert();
  }

}
