package com.dropbox.pages.modal.windows;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static com.dropbox.App.filesPage;
import static com.dropbox.App.keyboard;

public class AddAccountPhotoMW extends BasePage implements ModalWindow {

   private final String ADD_PHOTO_HEADER = "//*[text()='Add an account photo']";
   private final String CLOSE_BUTTON = "//button[contains(@aria-label,'Close')]";
   private final String DONE_BUTTON = "//button/*[text() = 'Done']";
   private final String UPLOAD_FROM_COMPUTER = "//div[@id = 'account_photo-upload-button']";
   private final String SUCCESS_UPLOAD_TEXT = "//div[@class = 'top-text']/span";

   public AddAccountPhotoMW(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(ADD_PHOTO_HEADER);
   }

   @Override
   public void confirm() {
      click(DONE_BUTTON);
   }

   @Override
   public void cancel() {
      click(CLOSE_BUTTON);
   }

   public void uploadFromComputer(File file) {
      if (isLoaded()) {
         click(UPLOAD_FROM_COMPUTER);
         waitFor(600);
         keyboard.uploadFileFromWindowsOS(file);
      }
   }

   public String successUploadText(){
      setImplicitWaitBySeconds(8);
      String successText = find(SUCCESS_UPLOAD_TEXT).getText();
      setImplicitWaitBySeconds(DEFAULT_IMPLICIT_WAIT);
      return successText;
   }
}