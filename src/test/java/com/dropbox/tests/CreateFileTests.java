package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.helpers.ScreenshotHelper.takeScreenshot;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateFileTests extends Launcher {

   @BeforeClass
   public void setUpBeforeClass() {
      if (!accountMenu.isUserAuthorized()) {
         open.signInPage().signInAs(BASIC_USER_VOVK_ILLIA);
         homePage.isLoaded();
      }
      open.filesPage();
   }

   @BeforeMethod
   public void setUpBeforeTest() {
      if (!filesPage.isLoaded()) {
         open.filesPage();
      }
   }

   @AfterMethod
   public void tearDownAfterMethod() {
      takeScreenshot();
   }

   @Test
   @Feature("Create files")
   @Severity(SeverityLevel.NORMAL)
   public void createShortcut() {
      open.createNewFileMenu().shortcut();
      createShortcutMW.enterURL("google.com")
              .enterName("Google")
              .confirm();
      assertThat(filesPage.listContainsFile("Google")).isTrue();
   }

   @Test
   @Feature("Create files")
   @Severity(SeverityLevel.NORMAL)
   public void createFolder() {
      secondaryActionMenu.clickNewFolderButton();
      createFolderMW.enterFolderName("FolderName").confirm();
      assertThat(filesPage.getNumberOfAllFilesOnPage()).isEqualTo(0);
      assertThat(filesPage.getURL()).contains("FolderName");
   }
}