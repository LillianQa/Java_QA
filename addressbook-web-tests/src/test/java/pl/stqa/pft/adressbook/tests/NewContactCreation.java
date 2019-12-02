package pl.stqa.pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.Contacts;
import pl.stqa.pft.adressbook.model.newContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreation extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      StringBuilder xml = new StringBuilder();
      String line = reader.readLine();

      while (line != null) {
        xml.append(line);
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(newContactData.class);
      List<newContactData> contacts = (List<newContactData>) xstream.fromXML(xml.toString());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      StringBuilder json = new StringBuilder();
      String line = reader.readLine();

      while (line != null) {
        json.append(line);
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<newContactData> contacts = gson.fromJson(json.toString(), new TypeToken<List<newContactData>>() {
      }.getType()); // List<newContactData>.class
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testNewContactCreation (newContactData contact) throws InterruptedException {
    app.goTo().HomePage();
    Contacts before = app.contact().all();
    Thread.sleep(3000);
    app.contact().getContact(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}