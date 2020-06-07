package com.dropbox.pages.menus;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondaryActionMenu extends BasePage {

  private final String UPLOAD_FILES_BUTTON = "//div[text() = 'Upload files']";
  private final String UPLOAD_FOLDER_BUTTON = "//div[text() = 'Upload folder']";
  private final String NEW_FOLDER_BUTTON = "//div[text() = 'New folder']";

  public SecondaryActionMenu(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(UPLOAD_FILES_BUTTON) & isDisplayed(UPLOAD_FOLDER_BUTTON);
  }

  public void clickUploadFilesButton() {
    click(UPLOAD_FILES_BUTTON);
  }

  public void clickUploadFolderButton() {
    click(UPLOAD_FOLDER_BUTTON);
  }

  public void clickNewFolderButton() {
    click(NEW_FOLDER_BUTTON);
  }
}