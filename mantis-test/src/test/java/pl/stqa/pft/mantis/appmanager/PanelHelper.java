package pl.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PanelHelper extends HelperBase {

  public PanelHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    type(By.xpath("//input[@name='username']"), username);
    type(By.xpath("//input[@name='password']"), password);
    click(By.xpath("//input[@class='button']"));
  }

  public void resetPassword() {
    wd.findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();
    wd.findElement(By.xpath("//a[contains(text(),'user7')]")).click();
    wd.findElement(By.cssSelector("form:nth-child(1) > input.button:nth-child(3)")).click();
  }
  public void finish(String confirmationLink) {
    wd.get(confirmationLink);
  }

  public void changePassword(String passworOld, String passwordNew) {
    type(By.xpath("//input[@name='password']"), passworOld);
    type(By.xpath("//input[@name='password_confirm']"), passwordNew);
    click(By.xpath("//input[@class='button']"));
  }

  public WebElement logout() {
    WebElement logout = wd.findElement(By.xpath("//a[contains(text(),'Logout')]"));
    return logout;
  }
}
