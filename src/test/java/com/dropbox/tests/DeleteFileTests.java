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

public class DeleteFileTests extends Launcher {

   @BeforeClass
   public void setUpBeforeClass() {
      if (!accountMenu.isUserAuthorized()) {
         open.signInPage().signInAs(BASIC_USER_VOVK_ILLIA);
         signInPage.isLoaded();
      }
   }

   @BeforeMethod
   public void setUpBeforeTest() {
      if (!filesPage.isLoaded()) {
         open.filesPage();
      }
   }

   @AfterMethod
   public void tearDownAfterMethod(){
      takeScreenshot();
   }

   @Test(priority = 1)
   @Feature("Deleting")
   @Severity(SeverityLevel.CRITICAL)
   public void deleteAFewFiles() {
      filesPage.selectAllFilesWhichContainText("Aivazovsky");
      open.contextMenu().delete();
      deleteModalWindow.confirm();
      assertThat(filesPage.selectAllFilesWhichContainText("Aivazovsky")).isEqualTo(0);
   }

   @Test(priority = 2)
   @Feature("Deleting")
   @Severity(SeverityLevel.NORMAL)
   public void deleteAllFiles() {
      filesPage.selectAllFiles();
      open.contextMenu().delete();
      deleteModalWindow.confirm();
      assertThat(filesPage.getNumberOfAllFilesOnPage()).isEqualTo(0);
   }
}