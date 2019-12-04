package pl.stqa.pft.adressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test2"));
      app.goTo().groupPage();
    }
  }

  @Test
  public void testDeleteGroup() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
    app.goTo().groupPage();
    Groups after = app.db().groups();
//    assertEquals(after.size(), before.size() - 1);
    assertThat(app.group().getGroupCount(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUI();
    }
}

