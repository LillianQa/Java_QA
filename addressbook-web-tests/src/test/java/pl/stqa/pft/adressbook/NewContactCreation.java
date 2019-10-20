package pl.stqa.pft.adressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NewContactCreation {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/edit.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.id("LoginForm")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testNewContactCreation() throws Exception {
    gotoAddNewContactPage();
    fillNewContactForm(new newContactData("Admin", "Admnin2", "Title", "Company", "Poland", "+48 678 876 987", "admin@onet.pl"));
    submitNewContactForm();
  }

  private void submitNewContactForm() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillNewContactForm(newContactData newContactData) {
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(newContactData.getFirstname());
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(newContactData.getLastname());
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(newContactData.getTitle());
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(newContactData.getCompany());
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(newContactData.getHome());
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(newContactData.getMobilenumber());
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(newContactData.getEmail());
  }

  private void gotoAddNewContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
    }
  }
