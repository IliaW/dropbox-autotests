package com.dropbox.tests;

import com.dropbox.Launcher;
import com.dropbox.model.User;
import org.testng.annotations.*;

import static com.dropbox.App.*;
import static com.dropbox.model.UserType.BASIC_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class SignInTests extends Launcher {

  @DataProvider
  public static Object[][] invalidAuthorizationData() {
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
    if (!signInPage.isLoaded()) {
      if (accountMenu.isUserAuthorized()) {
        accountMenu.signOut();
      }
    }
    open.signInPage();
  }

  @Test
  public void signInWithValidData() {
    signInPage.signInAs(BASIC_USER);
    assertThat(homePage.isLoaded()).isTrue();
  }

  @Test(dataProvider = "invalidAuthorizationData")
  public void signInWithInvalidData(User invalidUser, String expectedErrorMessage) {
    signInPage.signInAs(invalidUser);
    signInPage.setImplicitWaitBySeconds(10);
    assertThat(expectedErrorMessage).isEqualTo(signInPage.getActualErrorMessage());
    signInPage.setImplicitWaitBySeconds(4);
  }

  @Test(enabled = false)
  public void signInWithRememberMeCheckbox() {
    signInPage.setRememberMeCheckbox()
            .signInAs(BASIC_USER);
    //TODO
  }

  @Test(enabled = false)
  public void signInWithoutRememberMeCheckbox() {
    signInPage.resetRememberMeCheckbox()
            .signInAs(BASIC_USER);
    // TODO
  }
}