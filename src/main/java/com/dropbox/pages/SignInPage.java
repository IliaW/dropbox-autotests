package com.dropbox.pages;

import com.dropbox.model.User;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static com.dropbox.App.homePage;
import static com.dropbox.data.CookiesData.COOKIES_BASIC_USER;

public class SignInPage extends BasePage {

  public final String URL = "https://www.dropbox.com/login";

  private final String EMAIL_FIELD = "//div[@class = 'text-input standard login-text-input login-email']//input";
  private final String PASSWORD_FIELD = "//div[@class = 'text-input standard login-text-input login-password']//input";
  private final String REMEMBER_ME_CHECKBOX = "//div[@class = 'checkbox standard remember-me checkbox-inline']//input";
  private final String SIGN_IN_BUTTON = "//div[@class = 'signin-text']";
  private final String SIGN_IN_HEADER = "//div[@class = 'login-register-header' and contains(text(),'Sign in')]";
  private final String LOGIN_TITLE_TEXT = "Login - Dropbox";
  private final String ERROR_MESSAGE = "//*[@class ='error-message']";
  private final String VERIFICATION_FRAME = "//iframe[contains(@title,'Please solve this puzzle')]";

  public SignInPage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(SIGN_IN_HEADER) & wd.getTitle().equals(LOGIN_TITLE_TEXT);
  }

  public void signInWithCookies() {
    for (Cookie c : COOKIES_BASIC_USER) {
      wd.manage().addCookie(c);
    }
    refreshPage();
  }

  /**
   * At first, the program tries to log in using stored cookies.
   * If this fails - authorization using user data.
   * After authorization with user data, the cookie file is updated.
   * Made to avoid  "I am not a robot" form.
   */

  public void signInAs(User user) {
    if (user.cookiesIsExist()) {
      System.out.println("Log in with COOKIES");
      try {
        for (Cookie c : user.getCookies()) {
          wd.manage().addCookie(c);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      refreshPage();
      if (!homePage.isLoaded()) {
        System.out.println("Log in with COOKIES FAILED.");
        user.deleteCookiesFile();
        signInAs(user);
      }
    } else {
      System.out.println("Log in with EMAIL");
      enter(user.getLogin(), into(EMAIL_FIELD));
      enter(user.getPass(), into(PASSWORD_FIELD));
      click(SIGN_IN_BUTTON);
      homePage.isLoaded();
      try {
        user.saveCookies(wd.manage().getCookies());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public SignInPage setRememberMeCheckbox() {
    setCheckbox(REMEMBER_ME_CHECKBOX);
    return this;
  }

  public SignInPage resetRememberMeCheckbox() {
    resetCheckbox(REMEMBER_ME_CHECKBOX);
    return this;
  }

  // Check
  public String getActualErrorMessage() {
    try {
      setImplicitWaitBySeconds(10);
      String error = find(ERROR_MESSAGE).getText();
      setImplicitWaitBySeconds(DEFAULT_IMPLICIT_WAIT);
      return error;
    } catch (NoSuchElementException e) {
      e.printStackTrace();
      return null;
    }
  }

  public boolean isVerificationFramePresent() {
    try {
      wd.switchTo().frame(VERIFICATION_FRAME);
      return true;
    } catch (NoSuchFrameException e) {
      e.printStackTrace();
      return false;
    }
  }
}