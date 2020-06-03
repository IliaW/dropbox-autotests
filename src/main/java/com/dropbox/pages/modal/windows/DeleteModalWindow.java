package com.dropbox.pages.modal.windows;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteModalWindow extends BasePage implements ModalWindow {

  private final String DELETE_HEADER = "//div[contains(@class,'modal-header') and contains(text(),'Delete')]";
  private final String DELETE_BUTTON = "//button/span[text() = 'Delete']";
  private final String CANCEL_BUTTON = "//button/span[text() = 'Cancel']";

  public DeleteModalWindow(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(DELETE_HEADER);
  }

  @Override
  public void confirm() {
    this.isLoaded();
    click(DELETE_BUTTON);
    refreshPage();
  }

  @Override
  public void cancel() {
    this.isLoaded();
    click(CANCEL_BUTTON);
  }
}