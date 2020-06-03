package com.dropbox;

import com.dropbox.helpers.NavigationHelper;
import com.dropbox.pages.*;
import com.dropbox.pages.menus.AccountMenu;
import com.dropbox.pages.menus.ContextMenu;
import com.dropbox.pages.menus.CreateNewFileMenu;
import com.dropbox.pages.other.Search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class App {

  public static WebDriver driver;
  public static WebDriverWait wait;

  // Pages
  public static SignInPage signInPage;
  public static HomePage homePage;
  public static FilesPage filesPage;
  public static SharedPage sharedPage;
  public static DeletedFilesPage deletedFilesPage;
  public static SettingsPage settingsPage;

  // Menus
  public static AccountMenu accountMenu;
  public static ContextMenu contextMenu;
  public static CreateNewFileMenu createNewFileMenu;

  // Other features
  public static Search search;

  // Helpers
  public static NavigationHelper open;

  public void init(String browserType) {
    switch (browserType) {
      case (CHROME):
        driver = new ChromeDriver();
        break;
      case (FIREFOX):
        driver = new FirefoxDriver();
        break;
    }
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 4);
    driver.manage().window().maximize();

    signInPage = new SignInPage(driver, wait);
    homePage = new HomePage(driver, wait);
    filesPage = new FilesPage(driver, wait);
    sharedPage = new SharedPage(driver, wait);
    deletedFilesPage = new DeletedFilesPage(driver, wait);
    settingsPage = new SettingsPage(driver, wait);

    accountMenu = new AccountMenu(driver, wait);
    contextMenu = new ContextMenu(driver, wait);
    createNewFileMenu = new CreateNewFileMenu(driver, wait);

    search = new Search(driver, wait);
    open = new NavigationHelper();
  }

  public void stop() {
    driver.quit();
    driver = null;
  }

//  File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//try {
//    Files.copy(tempFile, new File("screen.png"));
//  } catch (IOException e) {
//    e.printStackTrace();
//  }

}