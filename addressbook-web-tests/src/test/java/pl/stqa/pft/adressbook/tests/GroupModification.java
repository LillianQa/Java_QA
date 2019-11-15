package pl.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModification extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1").withHeader("test2"));
    }
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.group().list();
    int index = before.size() -1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId()).withName("test1").withHeader("test3").withFooter("test4");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() -1);
    before.add(group);
    Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId); // = (g1, g2) -> Integer.compare(g1.getId(), g2.getId())
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // implementacja zbioru, przekształcamy listy na zbiory


  }
}
