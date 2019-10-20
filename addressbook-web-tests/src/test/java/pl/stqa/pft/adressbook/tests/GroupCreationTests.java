package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test3", "test4"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnGroupPage();
  }

}
