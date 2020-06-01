package com.dropbox.pages;

import com.dropbox.model.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    return isDisplayed(SIGN_IN_HEADER) && wd.getTitle().equals(LOGIN_TITLE_TEXT);
  }

  public void signInAs(User user) {
    enter(user.getLogin(), into(EMAIL_FIELD));
    enter(user.getPass(), into(PASSWORD_FIELD));
    click(SIGN_IN_BUTTON);
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
      return find(ERROR_MESSAGE).getText();
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