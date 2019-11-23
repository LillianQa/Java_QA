package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.List;
import java.util.Set;


public class DeleteContact extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2")
              .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), true);

    }

  }

  @Test
  public void testDeleteContact() throws InterruptedException {
    Thread.sleep(1000);
    Set<newContactData> before = app.contact().all();
    newContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.group().switchtoAlert();
    app.goTo().HomePage();
    Thread.sleep(1000);
    Set<newContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact); // we need to remove given element before comparision the list
    Assert.assertEquals(before, after);

  }

}
