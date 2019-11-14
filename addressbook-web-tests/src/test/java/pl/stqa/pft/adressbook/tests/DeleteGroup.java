package pl.stqa.pft.adressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqa.pft.adressbook.model.GroupData;

import java.util.List;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() -1); // we need to remove given element before comparision the list
    Assert.assertEquals(before, after);
    }
  }

