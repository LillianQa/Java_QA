package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pl.stqa.pft.adressbook.model.GroupData;

public class GroupHelper extends HelperBase {
  public GroupHelper (WebDriver wd) {
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

  public  void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    click(By.name("selected[]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void switchtoAlert() {
    wd.switchTo().alert().accept();
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm((group));
    submitGroupCreation();
    returnGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));

  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size(); // retrun all elements on the list
  }
}
