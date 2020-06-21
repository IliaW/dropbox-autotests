package com.dropbox.pages.modal.windows;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.dropbox.App.filesPage;

public class CreateShortcutMW extends BasePage implements ModalWindow {

   private final String CREATE_SHORTCUT_HEADER = "//div[text() = 'Create a new shortcut']";
   private final String URL_FIELD = "//input[@id = 'create-pointer--link_input']";
   private final String NAME_FIELD = "//input[@id = 'create-pointer--name_input']";
   private final String CANCEL_BUTTON = "//button[@class = 'mc-button mc-button-secondary']";
   private final String CREATE_BUTTON = "//button[@class = 'mc-button mc-button-primary']";
   private final String CREATING_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Creating')]";
   private final String CREATED_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Created')]";

   public CreateShortcutMW(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(CREATE_SHORTCUT_HEADER);
   }

   @Override
   public void confirm() {
      click(CREATE_BUTTON);
      filesPage.actionExecution(CREATING_SNACKBAR, CREATED_SNACKBAR);
      refreshPage();
   }

   @Override
   public void cancel() {
      click(CANCEL_BUTTON);
   }

   public CreateShortcutMW enterURL(String url) {
      if (isLoaded()) {
         enter(url, into(URL_FIELD));
      }
      return this;
   }

   public CreateShortcutMW enterName(String url) {
      if (isLoaded()) {
         enter(url, into(NAME_FIELD));
      }
      return this;
   }
}