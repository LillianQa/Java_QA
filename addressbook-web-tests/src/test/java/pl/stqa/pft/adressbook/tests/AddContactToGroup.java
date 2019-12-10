package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.Groups;
import pl.stqa.pft.adressbook.model.newContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test2"));
      app.goTo().groupPage();
    }
  }

  @BeforeMethod()
  public void ensurePrecondition1() throws InterruptedException {
    app.goTo().HomePage();
    app.contact().selectAllInTheListOfGroup();
    if (app.db().contact().size() == 0) {
      app.goTo().HomePage();
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2").withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), true);
      app.goTo().HomePage();
    }
  }

  @Test
  public void testAddContactToGroup() throws InterruptedException {

    app.goTo().HomePage();
    Contacts beforecontacts = app.db().contact();
    Groups beforegroup = app.db().groups();
    GroupData chooseGroupFromList = beforegroup.iterator().next();
    newContactData deletedContactFromGroup = beforecontacts.iterator().next();
    GroupData group = new GroupData().withId(chooseGroupFromList.getId());
    newContactData contact = new newContactData().withId(deletedContactFromGroup.getId());
    app.contact().selectGroupFromMenuLisWithHelpOfId(group);
    Groups beforegroups1 = app.db().groups();
    app.contact().selectAllInTheListOfGroup();
    app.goTo().HomePage();
    app.contact().selectContactFromList(contact);
    app.contact().addContactToGroup();
    Thread.sleep(1000);
    app.goTo().HomePage();
    app.contact().selectGroupFromMenuLisWithHelpOfId(group);


//    assertThat(app.contact().getContactCount(), equalTo(beforecontacts1.size() + 1));

    Groups after = app.db().groups();
    assertThat(after, equalTo(
            beforegroups1.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }
}
