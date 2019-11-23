package pl.stqa.pft.adressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NewContactCreation extends TestBase {



  @Test
  public void testNewContactCreation() {
    app.goTo().HomePage();
    Set<newContactData> before = app.contact().all();
    newContactData contactData = new newContactData().withFirstname("Admin").withLastname("Admin2")
            .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987");
    app.contact().getContact(contactData, true);
    app.goTo().HomePage();
    Set<newContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contactData);
    Assert.assertEquals(before, after); // przekształcenie listy w zbiory
  }

}
