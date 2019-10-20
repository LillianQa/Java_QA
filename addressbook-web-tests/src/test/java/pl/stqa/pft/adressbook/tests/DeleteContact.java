package pl.stqa.pft.adressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class DeleteContact extends TestBase {

  FirefoxDriver wd;

  @Test
  public void testDeleteContact() {

    app.getGroupHelper().gotoHomePage();
    app.getGroupHelper().chooseContact();
    app.getGroupHelper().deleteContact();
    wd.switchTo().alert().accept();
    app.getGroupHelper().returnGroupPage();
  }

}
