package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.List;


public class DeleteContact extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2")
              .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), false);

    }

  }

  @Test(enabled = false)
  public void testDeleteContact() {

    List<newContactData> before = app.contact().list();
    int index = before.size() -1;
    app.contact().delete(index);
    app.group().switchtoAlert();
    app.goTo().HomePage();
    List<newContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index); // we need to remove given element before comparision the list
    Assert.assertEquals(before, after);

  }

}
