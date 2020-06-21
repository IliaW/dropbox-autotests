package com.dropbox.pages.modal.windows;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeYourNameMW extends BasePage implements ModalWindow {

   private final String CHANGE_YOUR_NAME_HEADER = "//h1[@id = 'db-modal-title']/*[text() = 'Change your name']";

   private final String CHANGE_NAME_BUTTON = "//button[text() = 'Change name']";
   private final String CANCEL_BUTTON = "//button[text() = 'Cancel']";
   private final String FIRST_NAME_FIELD = "//input[@name = 'fname']";
   private final String LAST_NAME_FIELD = "//input[@name = 'lname']";
   private final String ERROR_MESSAGE_OF_FIRS_NAME = "//input[@name = 'fname']/../../*[contains(@id, 'error-message')]";
   private final String ERROR_MESSAGE_OF_LAST_NAME = "//input[@name = 'lname']/../../*[contains(@id, 'error-message')]";

   public ChangeYourNameMW(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(CHANGE_YOUR_NAME_HEADER);
   }

   @Override
   public void confirm() {
      if (isLoaded()) {
         click(CHANGE_NAME_BUTTON);
         waitFor(2400);
      }
   }

   @Override
   public void cancel() {
      click(CANCEL_BUTTON);
   }

   public ChangeYourNameMW setFirsName(String firsName) {
      enter(firsName, into(FIRST_NAME_FIELD));
      return this;
   }

   public ChangeYourNameMW setLastName(String lastName) {
      enter(lastName, into(LAST_NAME_FIELD));
      return this;
   }

   public String getActualErrorMessageOfFirstField() {
      return find(ERROR_MESSAGE_OF_FIRS_NAME).getText();
   }

   public String getActualErrorMessageOfLastField() {
      return find(ERROR_MESSAGE_OF_LAST_NAME).getText();
   }

   public boolean changeNameButtonIsClickable() {
      setExplicitWaitBySeconds(0);
      boolean b = isClickable(CHANGE_NAME_BUTTON);
      setExplicitWaitBySeconds(DEFAULT_EXPLICIT_WAIT);
      return b;
   }
}