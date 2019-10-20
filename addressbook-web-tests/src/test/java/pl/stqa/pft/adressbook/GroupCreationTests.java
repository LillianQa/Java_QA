package pl.stqa.pft.adressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test3", "test4"));
    submitGroupCreation();
    returnGroupPage();
  }

}
