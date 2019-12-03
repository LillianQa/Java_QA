package pl.stqa.pft.adressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends ForwardingSet<newContactData> {


  private Set<newContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<newContactData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<newContactData>();
  }

  public Contacts(List<newContactData> contacts) {
    this.delegate = new HashSet<newContactData>(contacts);
  }

  @Override
  protected Set<newContactData> delegate() {
    return delegate;
  }

  public Contacts withAdded(newContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(newContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
