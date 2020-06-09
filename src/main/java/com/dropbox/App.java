package com.dropbox;

import com.dropbox.helpers.NavigationHelper;
import com.dropbox.helpers.ScreenshotHelper;
import com.dropbox.pages.*;
import com.dropbox.pages.menus.AccountMenu;
import com.dropbox.pages.menus.CreateNewFile;
import com.dropbox.pages.menus.PrimaryActionMenu;
import com.dropbox.pages.menus.SecondaryActionMenu;
import com.dropbox.pages.modal.windows.DeleteModalWindow;
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
   public static DeletedFilesPage deletedFilesPage;
   public static SettingsPage settingsPage;

   // Menus
   public static AccountMenu accountMenu;
   public static CreateNewFile createNewFile;
   public static PrimaryActionMenu primaryActionMenu;
   public static SecondaryActionMenu secondaryActionMenu;

   // Modal Windows
   public static DeleteModalWindow deleteModalWindow;

   // Other
   public static Search search;

   // Helpers
   public static NavigationHelper open;

   public void init(String browser) {
      switch (browser) {
         case CHROME:
            driver = new ChromeDriver();
            break;
         case FIREFOX:
            driver = new FirefoxDriver();
            break;
      }
      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
      wait = new WebDriverWait(driver, 4);
      driver.manage().window().maximize();

      signInPage = new SignInPage(driver, wait);
      homePage = new HomePage(driver, wait);
      filesPage = new FilesPage(driver, wait);
      deletedFilesPage = new DeletedFilesPage(driver, wait);
      settingsPage = new SettingsPage(driver, wait);

      accountMenu = new AccountMenu(driver, wait);
      createNewFile = new CreateNewFile(driver, wait);
      primaryActionMenu = new PrimaryActionMenu(driver, wait);
      secondaryActionMenu = new SecondaryActionMenu(driver, wait);

      deleteModalWindow = new DeleteModalWindow(driver, wait);

      search = new Search(driver, wait);
      open = new NavigationHelper();
   }

   public void stop() {
      driver.quit();
      driver = null;
   }
}