package pl.stqa.pft.adressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("Contact")
public class newContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String firstAndLastname;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  private String address;
  private String addressSecondary;
  @Expose
  private String title;
  @Expose
  private String company;
  @Expose
  private String home;
  @Expose
  private String mobilenumber;
  @Expose
  private String email;
  private String email2;
  private String email3;
  private String group;
  private String workmobile;
  private String allEmails;
  private String allAddress;
  private String allPhones;
  private File photo;


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

  public newContactData withGroup (String group) {
    this.group = group;
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

  public newContactData withPhoto(File photo) {
    this.photo = photo;
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

  public String getGroup() {
    return group;
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
    return photo;
  }

  @Override
  public String toString() {
    return "newContactData{" +
            "id=" + id +
            ", firstAndLastname='" + firstAndLastname + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", addressSecondary='" + addressSecondary + '\'' +
            ", title='" + title + '\'' +
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
}
