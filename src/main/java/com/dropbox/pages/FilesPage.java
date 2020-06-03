package com.dropbox.pages;

import com.dropbox.helpers.KeyEventHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class FilesPage extends BasePage {

  public final String URL = "https://www.dropbox.com/home";

  private final String FILES_TITLE_TEXT = "Files – Dropbox";
  private final String DROPBOX_HEADER = "//h1/span[text() = 'Dropbox']";
  private final String UPLOAD_FILES_BUTTON = "//div[text() = 'Upload files']";
  private final String UPLOAD_FOLDER_BUTTON = "//div[text() = 'Upload folder']";
  private final String NEW_FOLDER_BUTTON = "//div[text() = 'New folder']";
  private final String SHARE_BUTTON = "//";
  private final String OPEN_BUTTON = "//";
  private final String DOWNLOAD_BUTTON = "//";
  private final String UPLOADING_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Uploading')]";
  private final String UPLOADED_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Uploaded')]";

  private final KeyEventHelper keyEventHelper;

  public FilesPage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
    keyEventHelper = new KeyEventHelper();
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(DROPBOX_HEADER) & wd.getTitle().equals(FILES_TITLE_TEXT);
  }

  public void uploadFile(File file) {
    click(UPLOAD_FILES_BUTTON);
    keyEventHelper.uploadFileFromWindowsOS(file);
    if (isDisplayed(UPLOADING_SNACKBAR)) {
      setExplicitWaitBySeconds(15);
      isDisplayed(UPLOADED_SNACKBAR);
      setExplicitWaitBySeconds(4);
      refreshPage();
    }
  }

  public void uploadFolder(File folder) {
    click(UPLOAD_FOLDER_BUTTON);
    keyEventHelper.uploadFolderFromWindowsOS(folder);
    if (isDisplayed(UPLOADING_SNACKBAR)) {
      setExplicitWaitBySeconds(15);
      isDisplayed(UPLOADED_SNACKBAR);
      setExplicitWaitBySeconds(4);
      refreshPage();
    }
  }

  public void createNewFolder() {
    click(NEW_FOLDER_BUTTON);
  }

  public void selectItemFromList(String name) {
    click("//span[text() = '" + name + "']/ancestor::tr");
  }

  // replace to File or FilesPage
  public void setCheckboxOnItemFromList(String name) {
    click("//span[text() = '" + name + "']/ancestor::tr//input[@type = 'checkbox']");
  }
}