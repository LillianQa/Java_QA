package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);

  }

  public void submitNewContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillNewContactForm(newContactData newContactData, boolean creation) {
    type(By.name("firstname"), newContactData.getFirstname());
    type(By.name("lastname"), newContactData.getLastname());
    type(By.name("title"), newContactData.getTitle());
    type(By.name("company"), newContactData.getCompany());
    type(By.name("home"), newContactData.getHome());
    type(By.name("mobile"), newContactData.getMobilenumber());
    type(By.name("email"), newContactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoAddNewContactPage() {
    click(By.linkText("add new"));
  }

  public void chooseContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public boolean isContactPresent() {
    return isElementPresent(By.name("selected[]"));
  }

  public void getContact(newContactData contactData, boolean b) {
    gotoAddNewContactPage();
    fillNewContactForm((contactData), true);
    submitNewContactForm();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size(); // retrun all elements on the list
  }

  public List<newContactData> getContactList() {
    List<newContactData> contacts = new ArrayList<newContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("td.center:nth-child(1)")); // find all elements in td.center
    for (WebElement element : elements) {
      String firstname = element.getText();
      String lastname = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); // we use this to the comparision the list with help of unique value
      newContactData contact = new newContactData(id, firstname, lastname, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}