package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
      new Select(wd.findElement(By.name("new_group")));
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
    click(By.xpath("//tr[2]//td[8]"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public boolean isContactPresent() {
    return isElementPresent(By.name("selected[]"));
  }

  public void getContact(newContactData contacts, boolean b) {
    gotoAddNewContactPage();
    fillNewContactForm(contacts, true);
    submitNewContactForm();
  }

  public void modify(newContactData contacts) {
//    selectContactbyId(contacts.getId());
    editContact();
    fillNewContactForm(contacts, false);
    updateContact();
  }


  public void delete(newContactData contact) {
    selectContactbyId(contact.getId());
    deleteContact();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactbyId(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size(); // retrun all elements on the list
  }

  public Set<newContactData> all() {
    Set<newContactData> contacts = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("div:nth-child(4) form:nth-child(10) table.sortcompletecallback-applyZebra:nth-child(2) tbody:nth-child(1) > tr:nth-child(2)")); // find all elements in td.entry
    List<WebElement> cells = wd.findElements(By.tagName("td"));
    for (WebElement element : elements) {
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); // we use this to the comparision the list with help of unique value
      newContactData contact = new newContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }
}