package com.dropbox.tests;

import com.dropbox.Launcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SignOutTests extends Launcher {

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInWithCookies();
      assertThat(homePage.isLoaded()).isTrue();
    }
  }

  @Test(enabled = false)
  public void signOut() {
    open.accountMenu().signOut();
    assertThat(signInPage.isLoaded()).isTrue();
  }
}