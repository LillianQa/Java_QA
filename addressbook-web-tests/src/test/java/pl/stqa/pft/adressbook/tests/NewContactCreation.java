package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class NewContactCreation extends TestBase {


  @Test
  public void testNewContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<newContactData> before = app.getContactHelper().getContactList();
    newContactData contact = new newContactData("Admin", "Admin2", "Title",
            "Company", "Poland", "+48 678 876 987",
            "admin@onet.pl", "test1");
    app.getContactHelper().getContact(contact, true);
    app.getNavigationHelper().gotoHomePage();
    List<newContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max(Comparator.comparingInt(newContactData::getId)).get().getId());
    before.add(contact);
    Comparator<?super newContactData> byId = Comparator.comparingInt(newContactData::getId);

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // przekształcenie listy w zbiory
  }

}
