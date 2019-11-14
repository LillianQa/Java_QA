package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.List;


public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() {

    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().getContact(new newContactData("Admin", "Admnin2", "Title", "Company",
              "Poland", "+48 678 876 987", "admin@onet.pl", "test1"), true);
    }
    List<newContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().deleteContact();
    app.getGroupHelper().switchtoAlert();
    app.getNavigationHelper().gotoHomePage();
    List<newContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() -1); // we need to remove given element before comparision the list
    Assert.assertEquals(before, after);

  }

}
