package com.dropbox;

import com.dropbox.helpers.KeyboardHelper;
import com.dropbox.helpers.NavigationHelper;
import com.dropbox.pages.*;
import com.dropbox.pages.menus.AccountMenu;
import com.dropbox.pages.menus.CreateNewFileMenu;
import com.dropbox.pages.menus.PrimaryActionMenu;
import com.dropbox.pages.menus.SecondaryActionMenu;
import com.dropbox.pages.modal.windows.*;
import com.dropbox.pages.other.Search;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
   public static CreateNewFileMenu createNewFile;
   public static PrimaryActionMenu primaryActionMenu;
   public static SecondaryActionMenu secondaryActionMenu;

   // Modal Windows
   public static AddAccountPhotoMW addAccountPhotoMW;
   public static ChangeYourNameMW changeYourNameMW;
   public static CreateFolderMW createFolderMW;
   public static CreateShortcutMW createShortcutMW;
   public static DeleteMW deleteMW;
   public static UploadToMW uploadToMW;

   // Other
   public static Search search;
   public static Actions action;

   // Helpers
   public static NavigationHelper open;
   public static KeyboardHelper keyboard;

   public void init(String browser) {
      switch (browser) {
         case CHROME:
            driver = new ChromeDriver();
            break;
         case FIREFOX:
            driver = new FirefoxDriver();
            break;
      }
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      wait = new WebDriverWait(driver, 3);
      driver.manage().window().maximize();

      signInPage = new SignInPage(driver, wait);
      homePage = new HomePage(driver, wait);
      filesPage = new FilesPage(driver, wait);
      deletedFilesPage = new DeletedFilesPage(driver, wait);
      settingsPage = new SettingsPage(driver, wait);

      accountMenu = new AccountMenu(driver, wait);
      createNewFile = new CreateNewFileMenu(driver, wait);
      primaryActionMenu = new PrimaryActionMenu(driver, wait);
      secondaryActionMenu = new SecondaryActionMenu(driver, wait);

      addAccountPhotoMW = new AddAccountPhotoMW(driver, wait);
      changeYourNameMW = new ChangeYourNameMW(driver, wait);
      createFolderMW = new CreateFolderMW(driver, wait);
      createShortcutMW = new CreateShortcutMW(driver, wait);
      deleteMW = new DeleteMW(driver, wait);
      uploadToMW = new UploadToMW(driver, wait);

      search = new Search(driver, wait);
      open = new NavigationHelper();
      keyboard = new KeyboardHelper();
      action = new Actions(driver);
   }

   public void stop() {
      driver.quit();
      driver = null;
   }
}