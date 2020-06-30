package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.*;

import static com.dropbox.App.*;
import static com.dropbox.data.FilesData.ACCOUNT_PHOTO;
import static com.dropbox.helpers.ScreenshotHelper.takeScreenshot;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class SettingsTest extends Launcher {

   @BeforeClass
   public void setUpBeforeClass() {
      if (!accountMenu.isUserAuthorized()) {
         open.signInPage().signInAs(BASIC_USER_VOVK_ILLIA);
         homePage.isLoaded();
      }
      open.settingsPage();
   }

   @BeforeMethod
   public void setUpBeforeMethod() {
      if (!settingsPage.isLoaded()) {
         open.settingsPage();
      }
   }

   @AfterMethod
   public void tearDownAfterMethod() {
      takeScreenshot();
   }

   @DataProvider
   public static Object[][] validAccountNames() {
      return new Object[][]{
              {"1", "2"},
              {"This text contains 100 characters for checking the maximum allowable length of the text in the field",
                      "This text contains 100 characters for checking the maximum allowable length of the text in the field"},
              {"user@gmail.com", "!@#$%^&()_+"},
              {"沃夫克", "伊利亞"},
              {"וובק", "איליה"},
              {"Илья", "Вовк"},
              {"Illia", "Vovk"}
      };
   }

   @DataProvider
   public static Object[][] invalidAccountNames() {
      return new Object[][]{
              {"***", "///",
                      "Names can’t contain \" * / : < > ? \\ |",
                      "Names can’t contain \" * / : < > ? \\ |"},
              {"This text contains more then 100 characters for checking the maximum allowable length of the text in the field",
                      "This text contains more then 100 characters for checking the maximum allowable length of the text in the field",
                      "First name cannot be longer then 100 characters.",
                      "Surname cannot be longer then 100 characters."}
      };
   }

   // If test failed, remove cookie file from src\main\resources.
   @Test
   @Flaky
   @Feature("Settings")
   @Severity(SeverityLevel.NORMAL)
   public void addAccountPhoto() {
      settingsPage.editPhoto();
      addAccountPhotoMW.uploadFromComputer(ACCOUNT_PHOTO);
      assertThat(addAccountPhotoMW.successUploadText()).contains("Looking good!");
      addAccountPhotoMW.confirm();
   }

   @Test(dataProvider = "validAccountNames")
   @Feature("Settings")
   @Severity(SeverityLevel.NORMAL)
   public void enterValidAccountName(String firstName, String lastName) {
      settingsPage.editName();
      changeYourNameMW.setFirsName(firstName)
              .setLastName(lastName)
              .confirm();
      assertThat(settingsPage.isLoaded()).isTrue();
      assertThat(settingsPage.actualAccountName()).contains(firstName + " " + lastName);
   }

   @Test(dataProvider = "invalidAccountNames")
   @Feature("Settings")
   @Severity(SeverityLevel.NORMAL)
   public void enterInvalidAccountName(String firstName, String lastName,
                                       String expectedErrorMessageOfFirstField,
                                       String expectedErrorMessageOfLastField) {
      settingsPage.editName();
      changeYourNameMW.setFirsName(firstName)
              .setLastName(lastName);
      assertThat(changeYourNameMW.changeNameButtonIsClickable()).isFalse();
      assertThat(changeYourNameMW.getActualErrorMessageOfFirstField()).isEqualTo(expectedErrorMessageOfFirstField);
      assertThat(changeYourNameMW.getActualErrorMessageOfLastField()).isEqualTo(expectedErrorMessageOfLastField);
      changeYourNameMW.cancel();
   }
}