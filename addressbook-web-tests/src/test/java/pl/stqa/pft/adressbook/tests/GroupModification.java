package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.GroupData;

public class GroupModification extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", "test3", "test4"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnGroupPage();


  }
}
