package pl.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.Groups;
import pl.stqa.pft.adressbook.model.newContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test2"));
      app.goTo().groupPage();
    }
  }

  @BeforeMethod
  public void ensurePrecondition1() {
    if (app.db().contact().size() == 0) {
      app.goTo().HomePage();
      app.contact().getContact(new newContactData().withFirstname("Admin").withLastname("Admin2").withTitle("Title").withCompany("Company").withHome("Poland").withEmail("admin@onet.pl").withMobilenumber("+48 678 876 987"), true);
      app.goTo().HomePage();
    }
  }

  @BeforeMethod
  public void ensurePrecondition2() throws InterruptedException {

    Groups beforegroup = app.db().groups();
    GroupData chooseGroupFromList = beforegroup.iterator().next();
    GroupData group = new GroupData().withId(chooseGroupFromList.getId());
    app.goTo().HomePage();
    app.contact().selectGroupFromMenuLisWithHelpOfId(group);
    if (app.db().contact().size() == 0) {
      app.contact().selectAllInTheListOfGroup();
      Contacts beforecontacts = app.db().contact();
      newContactData deletedContactFromGroup = beforecontacts.iterator().next();
      newContactData contact = new newContactData().withId(deletedContactFromGroup.getId());
      app.contact().selectContactFromList(contact);
      app.contact().addContactToGroup();

    }
  }

  @Test
  public void testDeleteContactFromGroup() throws InterruptedException {
    Contacts beforecontacts = app.db().contact();
    Groups beforegroup = app.db().groups();
    GroupData chooseGroupFromList = beforegroup.iterator().next();
    newContactData deletedContactFromGroup = beforecontacts.iterator().next();
    GroupData group = new GroupData().withId(chooseGroupFromList.getId());
    newContactData contact = new newContactData().withId(deletedContactFromGroup.getId());
    app.goTo().HomePage();
    app.contact().selectGroupFromMenuLisWithHelpOfId(group);
    Thread.sleep(1000);
    Groups beforegroups1 = app.db().groups();
    app.contact().selectContactFromList(contact);
    app.contact().removeContactFromGroup();
    app.goTo().HomePage();
    app.contact().selectGroupFromMenuLisWithHelpOfId(group);
    Thread.sleep(1000);

    assertThat(app.contact().getContactCount(), equalTo(beforegroups1.size()));

    Groups after = app.db().groups();
    assertThat(after, equalTo(beforegroups1.without(group)));
    verifyContactListInUI();

  }
}
