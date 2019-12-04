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
    // tutaj uzywam BufferReader zeby uzyskac dostep do metody .readLine ktora pozwala czytac pliki
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      StringBuilder xml = new StringBuilder();
      String line = reader.readLine();

      // bez cyklu czyta tylko jedna linijke wiec uzywam while
      while (line != null) {
        xml.append(line);
        line = reader.readLine(); // na kazdej kolejnej iteracji czyta cykl
      }
      XStream xstream = new XStream(); // nowy obiekt
      xstream.processAnnotations(newContactData.class);
      List<newContactData> contacts = (List<newContactData>) xstream.fromXML(xml.toString()); // wywolujemy metode jako liste danych
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator(); // zwracamy kontakty jako potok (stream())
      // i zwijamy to w tablice z pomoca map i anonimowej funkcji. Wywolujemy collect ktora z potoku zbiera z powrotem dane w liste i zwracamy iterator
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


  @Test(dataProvider = "validContactsFromJson") // tutaj wskazujemy z jakich danych ma korzystac nasz test
  public void testNewContactCreation (newContactData contact) throws InterruptedException {
    app.goTo().HomePage();
    Contacts before = app.db().contact();
    app.contact().getContact(contact, true);
    app.goTo().HomePage();
    Thread.sleep(3000);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.db().contact();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }

}