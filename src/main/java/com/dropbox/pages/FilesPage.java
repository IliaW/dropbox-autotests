package com.dropbox.pages;

import com.dropbox.helpers.KeyboardHelper;
import com.dropbox.model.DropboxFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class FilesPage extends BasePage {

  public final String URL = "https://www.dropbox.com/home";

  private final String FILES_TITLE_TEXT = "Files – Dropbox";
  private final String DROPBOX_HEADER = "//h1/span[text() = 'Dropbox']";
  private final String SELECT_ALL_CHECKBOX = "//tr[@class = 'mc-table-row mc-table-head-row']//label";
  private final String UPLOADING_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Uploading')]";
  private final String UPLOADED_SNACKBAR = "//p[@class ='mc-snackbar-title' and contains(text(),'Uploaded')]";

  private final KeyboardHelper keyboardHelper;

  public FilesPage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
    keyboardHelper = new KeyboardHelper();
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(DROPBOX_HEADER) & wd.getTitle().equals(FILES_TITLE_TEXT);
  }

  public void uploadFile(File file) {
    waitFor(600);
    keyboardHelper.uploadFileFromWindowsOS(file);
    actionExecution(UPLOADING_SNACKBAR, UPLOADED_SNACKBAR);
    refreshPage();
  }

  public void uploadFolder(File folder) {
    waitFor(600);
    keyboardHelper.uploadFolderFromWindowsOS(folder);
    actionExecution(UPLOADING_SNACKBAR, UPLOADED_SNACKBAR);
    refreshPage();
  }

  public int selectAllFilesWhichContainText(String text) {
    List<DropboxFile> list = getListOfAllFilesOnPage();
    int counterOfFilesWithTheText = 0;
    for (DropboxFile file : list) {
      if (file.getName().contains(text)) {
        ++counterOfFilesWithTheText;
        actions.moveToElement(file.getCheckboxLocator()).click().perform();
      }
    }
    return counterOfFilesWithTheText;
  }

  public void selectAllFiles() {
    actions.moveToElement(find(SELECT_ALL_CHECKBOX)).click().perform();
  }
}