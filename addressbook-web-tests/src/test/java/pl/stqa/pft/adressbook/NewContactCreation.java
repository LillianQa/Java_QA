package pl.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class NewContactCreation extends TestBase{


  @Test
  public void testNewContactCreation() {
    gotoAddNewContactPage();
    fillNewContactForm(new newContactData("Admin", "Admnin2", "Title", "Company", "Poland", "+48 678 876 987", "admin@onet.pl"));
    submitNewContactForm();
  }

}
