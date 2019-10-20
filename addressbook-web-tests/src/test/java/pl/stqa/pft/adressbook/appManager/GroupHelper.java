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
}
