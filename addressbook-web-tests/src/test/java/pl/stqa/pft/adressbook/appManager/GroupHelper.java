package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.adressbook.model.GroupData;

public class GroupHelper extends HelperBase{

  public GroupHelper(FirefoxDriver wd) {
    super(wd);
  }

  public  void returnGroupPage() {
    click(By.linkText("group page"));
  }

  public  void submitGroupCreation() {
    click(By.name("submit"));
  }

  public  void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public  void initGroupCreation() {
    click(By.name("new"));
  }

  public  void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public  void selectGroup() {
    click(By.name("selected[]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public void chooseContact() {
    click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//*[@id='maintable']/tbody[1]/tr[2]/td[8]/a[1]/img[1]"));
  }

  public void editContact() {
    click(By.xpath("//td[8]/a/img"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

}
