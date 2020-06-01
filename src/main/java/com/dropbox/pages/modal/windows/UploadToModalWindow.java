package com.dropbox.pages.modal.windows;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Step to open:
 * 1. Open "Home" page
 * 2. Click [Upload files] in menu at the right of the page
 * 3. Choose files and click OK
 */

public class UploadToModalWindow extends BasePage implements ModalWindow {

  private final String UPLOAD_TO_HEADER = "//div[contains(@class,'modal-header') and text() = 'Upload to…']";
  private final String UPLOAD_BUTTON = "//button/span[text() = 'Upload']";
  private final String CANCEL_BUTTON = "//button/span[text() = 'Cancel']";

  public UploadToModalWindow(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(UPLOAD_TO_HEADER);
  }

  @Override
  public void confirm() {
    click(UPLOAD_BUTTON);
  }

  @Override
  public void cancel() {
    click(CANCEL_BUTTON);
  }

  public void useFolder(String folderName) {
    click(String.format("//tr[contains(@class,'folder_picker_row')]//div[text() = '%s']", folderName));
  }
}