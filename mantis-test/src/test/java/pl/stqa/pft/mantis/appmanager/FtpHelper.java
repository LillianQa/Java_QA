package pl.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

  private final ApplicationManager app;
  private FTPClient ftp;

  // Inicialization, create a FTPClient
  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient();
  }

  // Upload a new file
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password")); // dostajemy sie do serwera
    ftp.deleteFile(backup); // na wszelki wypadek usuwamy backup
    ftp.rename(target, backup); // przemianowujemy usuniety plik
    ftp.enterLocalPassiveMode(); // tryb pasywny transferu danych, powiazana z ograniczeniami serwera
    ftp.storeFile(target, new FileInputStream(file)); // przenosi sie lokalny plik i robi sie input plik do czytania danych binarnych
    ftp.disconnect();
  }

  // Restore old file
  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host")); // polaczenie ze zdalna maszyna
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
