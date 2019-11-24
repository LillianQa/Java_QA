package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.newContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class NewContactCreation extends TestBase {


  @Test
  public void testNewContactCreation() {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    newContactData contactData = new newContactData().withFirstname("Admin").withLastname("Admin2")
            .withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987");
    app.contact().getContact(contactData, true);
    app.goTo().HomePage();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);
    contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contactData);
    assertEquals(before, after); // przekształcenie listy w zbiory

    assertThat(after, equalTo(
            before.withAdded(contactData.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
