package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;


public class DeleteContact extends TestBase {

  @Test
  public void testDeleteContact() {

    app.getGroupHelper().gotoHomePage();
    app.getGroupHelper().chooseContact();
    app.getGroupHelper().deleteContact();
    app.getGroupHelper().switchtoAlert();
    app.getGroupHelper().returnGroupPage();
  }

}
