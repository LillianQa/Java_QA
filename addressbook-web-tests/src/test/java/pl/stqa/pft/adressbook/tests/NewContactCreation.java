package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class NewContactCreation extends TestBase {


  @Test(enabled = false)
  public void testNewContactCreation() {
    app.goTo().HomePage();
    List<newContactData> before = app.contact().list();
    newContactData contact = new newContactData().withFirstname("Admin").withLastname("Admin2")
            .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987");
    app.contact().getContact(contact, true);
    app.goTo().HomePage();
    List<newContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max(Comparator.comparingInt(newContactData::getId)).get().getId());
    before.add(contact);
    Comparator<?super newContactData> byId = Comparator.comparingInt(newContactData::getId);

    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // przekształcenie listy w zbiory
  }

}
