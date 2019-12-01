package pl.stqa.pft.adressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pl.stqa.pft.adressbook.model.newContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data Format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<newContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }

  }

  private void saveAsJson(List<newContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<newContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(newContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();

  }
  private void saveAsCsv(List<newContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (newContactData contact : contacts) { // przechodzimy po wszystkich kontaktach ktore znajduja sie na liscie contacts
      writer.write(String.format("%s; %s; %s; %s; %s; %s; %s\n", contact.getFirstname(), contact.getLastname(),
              contact.getTitle(), contact.getCompany(),
              contact.getHomePhone(), contact.getMobilePhone(),
              contact.getEmail()));
    }
    writer.close();
  }

  private List<newContactData> generateContacts(int count) {

    List<newContactData> contacts = new ArrayList<newContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new newContactData()
              .withFirstname(String.format("Ania %s", i))
              .withLastname(String.format("Kowalska %s", i))
              .withTitle(String.format("Test %s", i))
              .withCompany(String.format("Anonim %s", i))
              .withHome(String.format("+48 123 456 765 %s", i))
              .withMobilenumber(String.format("23323 %s", i))
              .withEmail(String.format("something@gmail.com %s", i)));
    }
    return contacts;
  }
}

