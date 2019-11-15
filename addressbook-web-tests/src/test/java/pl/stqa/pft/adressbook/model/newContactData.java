package pl.stqa.pft.adressbook.model;

public class newContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String title;
  private String company;
  private String home;
  private String mobilenumber;
  private String email;
  private String group;

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

  public newContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public newContactData withLastname(String lastname) {
    this.lastname = lastname;
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

  public newContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getHome() {
    return home;
  }

  public String getMobilenumber() {
    return mobilenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "newContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    newContactData that = (newContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
