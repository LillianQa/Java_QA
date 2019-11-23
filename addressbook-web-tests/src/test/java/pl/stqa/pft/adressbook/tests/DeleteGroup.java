package pl.stqa.pft.adressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqa.pft.adressbook.model.GroupData;

import java.util.List;
import java.util.Set;


public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withFooter("test2"));
    }
  }

  @Test
  public void testDeleteGroup() {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup); // we need to remove given element before comparision the list
    Assert.assertEquals(before, after);
    }
}

