package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @Test
  public void testContactDetails() {

    app.goTo().HomePage();
    newContactData contact  = app.contact().all().iterator().next();
    newContactData contactInfoFromDetailsForm = app.contact().infoFromDetails(contact);

    assertThat(contact.getFirstAndLastname(), equalTo(mergeDetailsFirstAndLastname1(contactInfoFromDetailsForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeDetailsEmails(contactInfoFromDetailsForm)));
  }

  private String mergeDetailsFirstAndLastname1 (newContactData contact) {
    String result = "";
    if (contact.getFirstname() != null) {
      result = result + contact.getFirstname();
    }
    if (contact.getLastname() != null) {
      result = result + contact.getLastname();
    }
    return result;
  }

  private String mergeDetailsFirstAndLastname (newContactData contact) {

    return Arrays.asList(contact.getFirstname(), contact.getLastname())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergeDetailsEmails(newContactData contact) {

    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }



}
