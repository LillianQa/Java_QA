package pl.stqa.pft.adressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqa.pft.adressbook.model.GroupData;

import java.util.List;


public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1").withFooter("test2"));
    }
  }

  @Test
  public void testDeleteGroup() {
    List<GroupData> before = app.group().list();
    int index = before.size() -1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index); // we need to remove given element before comparision the list
    Assert.assertEquals(before, after);
    }
}

