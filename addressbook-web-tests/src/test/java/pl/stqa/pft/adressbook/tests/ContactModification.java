package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getGroupHelper().gotoHomePage();
    app.getGroupHelper().editContact();
    app.getGroupHelper().updateContact();
    app.getGroupHelper().gotoHomePage();

  }
}