package pl.stqa.pft.adressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NewContactCreation {
  private WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/edit.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys(username);
    driver.findElement(By.id("LoginForm")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys(password);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testNewContactCreation() throws Exception {
    gotoAddNewContactPage();
    fillNewContactForm(new newContactData("Admin", "Admnin2", "Title", "Company", "Poland", "+48 678 876 987", "admin@onet.pl"));
    submitNewContactForm();
  }

  private void submitNewContactForm() {
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillNewContactForm(newContactData newContactData) {
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys(newContactData.getFirstname());
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys(newContactData.getLastname());
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys(newContactData.getTitle());
    driver.findElement(By.name("company")).clear();
    driver.findElement(By.name("company")).sendKeys(newContactData.getCompany());
    driver.findElement(By.name("home")).clear();
    driver.findElement(By.name("home")).sendKeys(newContactData.getHome());
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys(newContactData.getMobilenumber());
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys(newContactData.getEmail());
  }

  private void gotoAddNewContactPage() {
    driver.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    }
  }
