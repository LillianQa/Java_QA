package pl.stqa.pft.adressbook;


import org.testng.annotations.*;


public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroup();
    returnGroupPage();
  }

}
