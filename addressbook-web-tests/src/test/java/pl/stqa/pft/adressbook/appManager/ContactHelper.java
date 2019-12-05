package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.newContactData;

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
    type(By.name("home"), newContactData.getHomePhone());
    type(By.name("mobile"), newContactData.getMobilePhone());
    type(By.name("email"), newContactData.getEmail());
    attach(By.name("photo"), newContactData.getPhoto());

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

  public void editContact(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();  }

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
    editContact(contacts.getId());
    fillNewContactForm(contacts, false);
    updateContact();
  }

  public void addContactToGroup() {
    wd.findElement(By.xpath("//input[@name='add']")).click();
  }

  public void selectGroupFromMenuLisWithHelpOfId(GroupData group) throws InterruptedException {
    Thread.sleep(1000);
    selectGroupFromMenuList(group.getId());
    Thread.sleep(1000);
  }

  public void selectContactFromList(newContactData contact) {
    selectContactbyId(contact.getId());
  }

  public void removeContactFromGroup() {
    wd.findElement(By.xpath("//input[@name='remove']")).click();
  }

  public void selectGroupFromMenuList(int id) {
    wd.findElement(By.xpath("//select[@name='group']")).click();
    wd.findElement(By.xpath("//option[@value='" + id + "']")).click();
  }

  public void selectAllInTheListOfGroup() throws InterruptedException {
    wd.findElement(By.xpath("//select[@name='group']")).click();
    wd.findElement(By.xpath("//option[contains(text(),'[all]')]")).click();

  }

  public void delete(newContactData contact) {
    selectContactbyId(contact.getId());
    deleteContact();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactbyId(int id) {
    wd.findElement(By.xpath("//input[@id='" + id + "']")).click();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size(); // retrun all elements on the list
  }

  private Contacts contactCache = null;

  public Contacts all() {

    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']")); // find all elements in td.entry
    List<WebElement> cells = wd.findElements(By.tagName("td"));
    for (WebElement element : elements) {
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String firstAndLastname = cells.get(2).getText() + cells.get(1).getText();
      String allAddress = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); // we use this to the comparision the list with help of unique value
      newContactData contact = new newContactData().withId(id)
              .withFirstAndLastname(firstAndLastname)
              .withFirstname(firstname).withLastname(lastname)
              .withAllEmails(allEmails)
              .withAllAddress(allAddress)
              .withAllPhones(allPhones);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

  public newContactData infoFromEditForm(newContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("textarea");
    String address2 = wd.findElement(By.name("address2")).getAttribute("textarea");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new newContactData().withId(contact.getId()).withFirstname(firstname)
            .withLastname(lastname).withAddress(address).withAddressSecondary(address2)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHome(home).withMobilenumber(mobile).withWorkMobile(work);
  }

  private void initContactModification (int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public newContactData infoFromDetails(newContactData contact) {
    initContactDetails(contact.getId());
    List<WebElement> wholePage = wd.findElements(By.xpath("//div[@id='content']"));
    String firstnameAndLastname = wd.findElement(By.cssSelector("html body div#container div#content b")).getAttribute("text");
    String email = wd.findElement(By.xpath("//div[@id='content']//a[1]")).getAttribute("text");
    String email2 = wd.findElement(By.xpath("//div[@id='content']//a[2]")).getAttribute("text");
    String email3 = wd.findElement(By.xpath("//div[@id='content']//a[3]")).getAttribute("text");
    String title = wd.findElement(By.xpath("//div[1]/div[4]/i[1]")).getAttribute("text");
    wd.navigate().back();
    return new newContactData().withId(contact.getId())
            .withFirstAndLastname(firstnameAndLastname)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withTitle(title);


  }

  private void initContactDetails(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();



  }
}