package pl.stqa.pft.adressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqa.pft.adressbook.appManager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception{
    app.stop();

  }

}
