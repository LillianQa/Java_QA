package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

public class NewContactCreation extends TestBase {


  @Test
  public void testNewContactCreation() {
    app.getContactHelper().getContact(new newContactData("Admin", "Admnin2", "Title",
            "Company", "Poland", "+48 678 876 987",
            "admin@onet.pl", "test1"), true);
  }

}
