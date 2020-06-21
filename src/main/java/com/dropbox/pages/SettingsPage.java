package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage extends BasePage {

   public final String URL = "https://www.dropbox.com/account";

   private final String ACCOUNT_HEADER = "//h1[text() = 'Personal account']";
   private final String EDIT_PHOTO_BUTTON = "//div[contains(@class,'preference-picture-row')]//button//*[text()='Edit']";
   private final String EDIT_NAME_BUTTON = "//div[contains(@class,'preference-name-row')]//button//*[text()='Edit']";
   private final String NAME_VALUE = "//div[contains(@class,'preference-name-row')]/span[@class = 'account-key-value-block__value']";

   public SettingsPage(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(ACCOUNT_HEADER);
   }

   public void editPhoto() {
      click(EDIT_PHOTO_BUTTON);
   }

   public void editName() {
      click(EDIT_NAME_BUTTON);
   }

   public String actualAccountName() {
      return find(NAME_VALUE).getText();
   }

}