package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.adressbook.model.newContactData;

public class ContactHelper extends HelperBase{
  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public  void submitNewContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public  void fillNewContactForm(newContactData newContactData) {
    type(By.name("firstname"), newContactData.getFirstname());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("title"), newContactData.getTitle());
    type(By.name("company"), newContactData.getCompany());
    type(By.name("home"), newContactData.getHome());
    type(By.name("mobile"), newContactData.getMobilenumber());
    type(By.name("email"), newContactData.getEmail());
  }

  public  void gotoAddNewContactPage() {
    click(By.linkText("add new"));
  }
}
