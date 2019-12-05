package pl.stqa.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;

// enity, column, type tycza sie polaczenia z baza danych wiec nie patrz na to
@Entity
@Table(name = "addressbook")
@XStreamAlias("Contact") // to dodajemy zeby w plikau xml naglowek zamiast sciezki pliku pokazywala sie nasza nazwa wlasna np Contact
public class newContactData extends Contacts {

  @Id
  @Column(name = "id")
  @XStreamOmitField // omijamy to pole w zapisie pliku xml, bo jest nam zbedne w danych tetsowych
  private int id = Integer.MAX_VALUE;

  @Transient
  private String firstAndLastname;

  @Expose
  @Column(name = "firstname")
  @Type(type= "string")
  private String firstname;

  @Expose
  @Column(name = "lastname")
  @Type(type= "string")
  private String lastname;

  @Expose
  @Column(name = "address")
  @Type(type= "text")
  private String address;

  @Expose
  @Column(name = "address2")
  @Type(type= "text")
  private String addressSecondary;

  @Expose
  @Column(name = "title")
  private String title;

  @Expose
  @Column(name = "company")
  private String company;

  @Expose
  @Column(name = "home")
  @Type(type= "text")
  private String home;

  @Expose
  @Column(name = "mobile")
  @Type(type= "text")
  private String mobilenumber;

  @Expose
  @Column(name = "email")
  @Type(type= "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type= "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type= "text")
  private String email3;

  @Expose
  @Type(type= "text")
  @Column(name = "work")
  private String workmobile;

  @Expose
  @Column(name = "photo")
  @Type(type= "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER) // wyciaganie wiele informacji za jednym razem
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id")) // zwiazek miedzy obiektami dwoch typow
  private Set<GroupData> groups = new HashSet<GroupData>(); // connect between Contact and Groups

  @Transient
  private String allEmails;

  @Transient
  private String allAddress;

  @Transient
  private String allPhones;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public newContactData withId (int id) {
    this.id = id;
    return this;
  }

  public newContactData withFirstAndLastname(String firstAndLastname) {
    this.firstAndLastname = firstAndLastname;
    return this;
  }

  public newContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public newContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public newContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public newContactData withAddressSecondary(String addressSecondary) {
    this.addressSecondary = addressSecondary;
    return this;
  }

  public newContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public newContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public newContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public newContactData withMobilenumber(String mobilenumber) {
    this.mobilenumber = mobilenumber;
    return this;
  }

  public newContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public newContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public newContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public newContactData withWorkMobile (String workmobile) {
    this.workmobile = workmobile;
    return this;
  }

  public newContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public newContactData withAllAddress(String allAddress) {
    this.allAddress = allAddress;
    return this;
  }

  public newContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public newContactData withPhoto(File photo, int i) {
    this.photo = photo.getPath();
    return this;
  }

  public String getFirstAndLastname() {
    return firstAndLastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getAddressSecondary() {
    return addressSecondary;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getHomePhone() {
    return home;
  }

  public String getMobilePhone() {
    return mobilenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getWorkPhone() {
    return workmobile;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAllAddress() {
    return allAddress;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public File getPhoto() {
    return new File("src/test/resources/photo.png");
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public String toString() {
    return "newContactData{" +
            "id=" + id +
//            ", firstAndLastname='" + firstAndLastname + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
//            ", address='" + address + '\'' +
//            ", addressSecondary='" + addressSecondary + '\'' +
//            ", title='" + title + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    newContactData that = (newContactData) o;

    if (id != that.id) return false;
//    if (firstAndLastname != null ? !firstAndLastname.equals(that.firstAndLastname) : that.firstAndLastname != null)
//      return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    return addressSecondary != null ? addressSecondary.equals(that.addressSecondary) : that.addressSecondary == null;
  }


  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public Spliterator<newContactData> spliterator() {
    return null;
  }
}
