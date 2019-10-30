package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;


public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteContact();
    app.getGroupHelper().switchtoAlert();
  }

}
