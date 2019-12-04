package pl.stqa.pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import pl.stqa.pft.adressbook.appManager.ApplicationManager;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.GroupData;
import pl.stqa.pft.adressbook.model.Groups;
import pl.stqa.pft.adressbook.model.newContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase  {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception{
    app.stop();

  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test" + m.getName() + "with parameters" + Arrays.asList(p)); // we need to convert output as a list, because there's no string
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] p) {
    logger.info("Start stop" + m.getName());

  }

  public void verifyGroupListInUI() {
    // in VM options press -DverifyUI=true
    if (Boolean.getBoolean("verifyGroupUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      // na wejscie przyjmuje grupe obiektow a na wyjsciu gruoupData jako nowy obiekt
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUI() {
    // in VM options press -DverifyUI=true
    if (Boolean.getBoolean("verifyContactUI")) {
      Contacts dbContacts = app.db().contact();
      Contacts uiContacts = app.contact().all();
      // na wejscie przyjmuje grupe obiektow a na wyjsciu gruoupData jako nowy obiekt
      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((g) -> new newContactData().withId(g.getId()).withFirstname(g.getFirstname()).withLastname(g.getLastname()))
              .collect(Collectors.toSet())));
    }
  }
}
