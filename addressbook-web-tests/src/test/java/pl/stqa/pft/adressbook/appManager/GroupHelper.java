package pl.stqa.pft.adressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


  public void selectGroupbyId(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm((group));
    submitGroupCreation();
    returnGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupbyId(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupbyId(group.getId());
    deleteSelectedGroup();
    groupCache = null;
    returnGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));

  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size(); // retrun all elements on the list
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }

    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); // find all elements in span.group
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); // we use this to the comparision the list with help of unique value
      groupCache.add(new GroupData().withId(id).withName(name));
    }

    return new Groups(groupCache);
  }
}
