package pl.stqa.pft.adressbook.model;

public class newContactData {
  private int id;
  private final String firstname;
  private final String lastname;
  private final String title;
  private final String company;
  private final String home;
  private final String mobilenumber;
  private final String email;
  private final String group;

  public newContactData(String firstname, String lastname, String title, String company, String home, String mobilenumber, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.title = title;
    this.company = company;
    this.home = home;
    this.mobilenumber = mobilenumber;
    this.email = email;
    this.group = group;
  }

  public newContactData(int id, String firstname, String lastname, String title, String company, String home, String mobilenumber, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.title = title;
    this.company = company;
    this.home = home;
    this.mobilenumber = mobilenumber;
    this.email = email;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
