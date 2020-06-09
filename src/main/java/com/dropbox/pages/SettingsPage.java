package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage extends BasePage {

   public final String URL = "https://www.dropbox.com/account";

   private final String HOME_HEADER = "//h1[text() = 'Personal account']";

   public SettingsPage(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(HOME_HEADER);
   }
}