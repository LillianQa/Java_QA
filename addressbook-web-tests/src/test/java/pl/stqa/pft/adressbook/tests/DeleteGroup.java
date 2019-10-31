package pl.stqa.pft.adressbook.tests;


import org.testng.annotations.*;
import pl.stqa.pft.adressbook.model.GroupData;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, "test4"));
    }
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnGroupPage();
  }

}
