package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeletedFilesPage extends BasePage {

  public final String URL = "https://www.dropbox.com/deleted_files";

  private final String DELETED_FILES_TITLE_TEXT = "Deleted files – Dropbox";
  private final String DELETED_FILES_HEADER = "//h1/span[text() = 'Deleted files']";

  public DeletedFilesPage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(DELETED_FILES_HEADER) & wd.getTitle().equals(DELETED_FILES_TITLE_TEXT);
  }
}
