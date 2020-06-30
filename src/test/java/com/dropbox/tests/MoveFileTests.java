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

public class MoveFileTests extends Launcher {

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

   @Test(enabled = false)
   @Feature("Move files")
   @Severity(SeverityLevel.MINOR)
   public void dragAndDropFileToFolder() {
      secondaryActionMenu.clickNewFolderButton();
      createFolderMW.enterFolderName("NewFolder_1").confirm();
      open.filesPage();
      secondaryActionMenu.clickNewFolderButton();
      createFolderMW.enterFolderName("NewFolder_2").confirm();
      open.filesPage();
      // don't work =(
      action.dragAndDrop(filesPage.find("//tr//*[contains(text(),'NewFolder_1')]"),
              filesPage.find("//tr//*[contains(text(),'NewFolder_2')]")).perform();
   }
}