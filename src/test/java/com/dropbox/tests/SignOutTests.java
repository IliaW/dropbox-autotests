package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class SignOutTests extends Launcher {

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInAs(BASIC_USER_VOVK_ILLIA);
    }
  }

  @Severity(SeverityLevel.CRITICAL)
  @Test()
  public void signOut() {
    open.accountMenu().signOut();
    assertThat(signInPage.isLoaded()).isTrue();
  }
}