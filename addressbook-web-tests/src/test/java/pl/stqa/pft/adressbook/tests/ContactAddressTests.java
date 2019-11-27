package pl.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.adressbook.model.newContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @Test
  public void testContactAddress() {

    app.goTo().HomePage();
    newContactData contact = app.contact().all().iterator().next();
    newContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllAddress(), equalTo(mergeAddress1(contactInfoFromEditForm)));
  }

  private String mergeAddress1 (newContactData contact) {
    String result = "";
    if (contact.getAddress() != null) {
      result = result + contact.getAddress();
    }
    if (contact.getAddressSecondary() != null) {
      result = result + contact.getAddressSecondary();
    }
    return result;
  }

  private String mergeAddresses(newContactData contact) {

    return Arrays.asList(contact.getAddress(), contact.getAddressSecondary())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
}
}
