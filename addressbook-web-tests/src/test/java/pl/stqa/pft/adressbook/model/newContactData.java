package pl.stqa.pft.adressbook.model;

public class newContactData {
  private final String firstname;
  private final String lastname;
  private final String title;
  private final String company;
  private final String home;
  private final String mobilenumber;
  private final String email;

  public newContactData(String firstname, String lastname, String title, String company, String home, String mobilenumber, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.title = title;
    this.company = company;
    this.home = home;
    this.mobilenumber = mobilenumber;
    this.email = email;
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
}
