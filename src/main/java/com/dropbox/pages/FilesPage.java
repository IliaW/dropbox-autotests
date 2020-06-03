package com.dropbox.pages;

import com.dropbox.helpers.KeyEventHelper;
import com.dropbox.model.DropboxFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class FilesPage extends BasePage {

  public final String URL = "https://www.dropbox.com/home";

  private final String FILES_TITLE_TEXT = "Files – Dropbox";
  private final String DROPBOX_HEADER = "//h1/span[text() = 'Dropbox']";
  private final String UPLOAD_FILES_BUTTON = "//div[text() = 'Upload files']";
  private final String UPLOAD_FOLDER_BUTTON = "//div[text() = 'Upload folder']";
  private final String NEW_FOLDER_BUTTON = "//div[text() = 'New folder']";
  private final String SELECT_ALL_CHECKBOX = "//input[@aria-label = 'Select all']";
  private final String UPLOADING_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Uploading')]";
  private final String UPLOADED_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Uploaded')]";
  private final String DELETING_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Deleting')]]";
  private final String DELETED_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Deleted')]]";

  private final KeyEventHelper keyEventHelper;

  public FilesPage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
    keyEventHelper = new KeyEventHelper();
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(DROPBOX_HEADER) & wd.getTitle().equals(FILES_TITLE_TEXT);
  }

  public void uploadFiles(File... file) {
    for (File f : file) {
      click(UPLOAD_FILES_BUTTON);
      keyEventHelper.uploadFileFromWindowsOS(f);
      execution(UPLOADING_SNACKBAR, UPLOADED_SNACKBAR);
    }
    refreshPage();
  }

  public void uploadFolder(File folder) {
    click(UPLOAD_FOLDER_BUTTON);
    keyEventHelper.uploadFolderFromWindowsOS(folder);
    execution(UPLOADING_SNACKBAR, UPLOADED_SNACKBAR);
    refreshPage();
  }

  public void selectAllFilesWhichContainText(String text) {
    List<DropboxFile> list = getListOfAllFilesOnPage();
    for (DropboxFile file : list) {
      if (file.getName().contains(text)) {
        setCheckbox(file.getCheckboxLocator());
      }
    }
  }

  public void selectAllFiles() {
    setCheckbox(SELECT_ALL_CHECKBOX);
  }

  public void createNewFolder() {
    click(NEW_FOLDER_BUTTON);
  }

  private void execution(String processing, String finish) {
    if (isDisplayed(processing)) {
      setExplicitWaitBySeconds(15);
      isDisplayed(finish);
      setExplicitWaitBySeconds(DEFAULT_IMPLICIT_WAIT);
    }
  }
}