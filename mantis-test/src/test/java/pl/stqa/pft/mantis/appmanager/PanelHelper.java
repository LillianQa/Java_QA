package pl.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PanelHelper extends HelperBase {

  public PanelHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    type(By.xpath("//input[@name='username']"), username);
    type(By.xpath("//input[@name='password']"), password);
    click(By.xpath("//input[@class='button']"));
  }

  public void getUser() {

    wd.findElement(By.xpath("//a[contains(text(),'Manage Users')]")).click();
    List<WebElement> usersTable = wd.findElements(By.cssSelector("table.width100:nth-child(8) tbody:nth-child(1) tr.row-2"));
    List<WebElement> cellsOfUsername = wd.findElements(By.cssSelector("tr.row-1 > td > a"));
    for (int i = 0; i < usersTable.size(); i++) {
      String userText = usersTable.get(i).getText();
      if (userText.contains("reporter")) {
        cellsOfUsername.get(i).click();
        return;
      }
    }
  }

  public String getEmail() {
    String email = wd.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
    return email;
  }

  public String getUsername() {
    String username = wd.findElement(By.cssSelector("form:nth-child(1) table.width75:nth-child(2) tbody:nth-child(1) tr.row-1:nth-child(2) > td:nth-child(2)")).getText();
    return username;
  }

  public void resetPassword() {
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
