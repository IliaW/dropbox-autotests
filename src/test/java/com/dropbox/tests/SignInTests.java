package com.dropbox.tests;

import com.dropbox.Launcher;
import com.dropbox.model.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.helpers.ScreenshotHelper.takeScreenshot;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInTests extends Launcher {

   @DataProvider
   public Object[][] incorrectAuthorizationCredential() {
      return new Object[][]{
              {new User("", ""),
                      "Please enter your email"},
              {new User("        ", "        "),
                      "Please enter your email"},
              {new User("", "qatest123456"),
                      "Please enter your email"},
              {new User("qatest.vovk@gmail.com", ""),
                      "Please enter your password"},
              {new User("qatest.vovk@gmailcom", "qatest123456"),
                      "The domain portion of the email address is invalid (the portion after the @: gmailcom)"},
              {new User("qatest.vovk@gmail.com", "qatest123"),
                      "Invalid email or password"},
              {new User("test.vovk@gmail.com", "qatest123567"),
                      "Invalid email or password"}
      };
   }

   @BeforeMethod
   public void setUpBeforeTest() {
      open.signInPage();
   }

   @AfterMethod
   public void tearDownAfterMethod(){
      takeScreenshot();
   }

   @Test(priority = 2)
   @Feature("Authorization")
   @Severity(SeverityLevel.BLOCKER)
   public void signIn() {
      signInPage.signInAs(BASIC_USER_VOVK_ILLIA);
      assertThat(homePage.isLoaded()).isTrue();
   }

   @Test(priority = 1, dataProvider = "incorrectAuthorizationCredential")
   @Feature("Authorization")
   @Severity(SeverityLevel.CRITICAL)
   public void signInWithIncorrectCredential(User userWithIncorrectCredential, String expectedErrorMessage) {
      signInPage.signInAs(userWithIncorrectCredential);
      assertThat(signInPage.getActualErrorMessage()).isEqualTo(expectedErrorMessage);
   }

   @Test(enabled = false)
   @Feature("Authorization")
   @Severity(SeverityLevel.MINOR)
   public void signInWithRememberMeCheckbox() {
      signInPage.setRememberMeCheckbox()
              .signInAs(BASIC_USER_VOVK_ILLIA);
      //TODO
   }

   @Test(enabled = false)
   @Feature("Authorization")
   @Severity(SeverityLevel.MINOR)
   public void signInWithoutRememberMeCheckbox() {
      signInPage.resetRememberMeCheckbox()
              .signInAs(BASIC_USER_VOVK_ILLIA);
      // TODO
   }
}