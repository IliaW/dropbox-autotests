package com.dropbox.pages;

import com.dropbox.model.User;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static com.dropbox.App.homePage;

public class SignInPage extends BasePage {

   public final String URL = "https://www.dropbox.com/login";

   private final String EMAIL_FIELD = "//div[@class = 'text-input standard login-text-input login-email']//input";
   private final String PASSWORD_FIELD = "//div[@class = 'text-input standard login-text-input login-password']//input";
   private final String REMEMBER_ME_CHECKBOX = "//div[@class = 'checkbox standard remember-me checkbox-inline']//input";
   private final String SIGN_IN_BUTTON = "//div[@class = 'signin-text']";
   private final String SIGN_IN_HEADER = "//div[@class = 'login-register-header' and contains(text(),'Sign in')]";
   private final String LOGIN_TITLE_TEXT = "Login - Dropbox";
   private final String ERROR_MESSAGE = "//*[@class ='error-message']";

   public SignInPage(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(SIGN_IN_HEADER) & wd.getTitle().equals(LOGIN_TITLE_TEXT);
   }

   /**
    * At first, the program tries to login using stored cookies.
    * If fails - authorization with user credentials.
    * After authorization with user credentials, the cookie file will created/updated.
    * Made to avoid  "I am not a robot" form on production stage.
    */
   public void signInAs(User user) {
      if (user.cookiesIsExist()) {
         System.out.println("Login with COOKIES");
         try {
            for (Cookie c : user.getSavedCookies()) {
               wd.manage().addCookie(c);
            }
            refreshPage();
            if (!homePage.isLoaded()) {
               System.out.println("Login with COOKIES: FAIL");
               user.deleteCookiesFile();
               signInAs(user);
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         System.out.println("Log in with EMAIL");
         enter(user.getLogin(), into(EMAIL_FIELD));
         enter(user.getPass(), into(PASSWORD_FIELD));
         click(SIGN_IN_BUTTON);
         try {
            if (getActualErrorMessage() == null && homePage.isLoaded()) {
               user.saveCookies(wd.manage().getCookies());
               System.out.println("Log in with EMAIL: SUCCESS");
            }
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
         setImplicitWaitBySeconds(6);
         String error = find(ERROR_MESSAGE).getText();
         setImplicitWaitBySeconds(DEFAULT_IMPLICIT_WAIT);
         return error;
      } catch (NoSuchElementException e) {
         e.printStackTrace();
         return null;
      }
   }
}