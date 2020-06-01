package com.dropbox.pages.modal.windows;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Step to open:
 * 1. Open "Files" page
 * 2. Click [New folder] in menu at the right of the page
 */

public class CreateFolderModalWindow extends BasePage implements ModalWindow {

  private final String CREATE_FOLDER_HEADER = "//div[contains(@class,'modal-header')]//div[text() = 'Create folder']";
  private final String FOLDER_NAME_FIELD = "//input[@id = 'new_folder_name_input']";
  private final String CANCEL_BUTTON = "//button[text() = 'Cancel']";
  private final String CREATE_BUTTON = "//button[text() = 'Create']";
  private final String ONLY_YOU_RADIO_BUTTON = "//label[text() = 'Only you']";
  private final String SPECIFIC_PEOPLE_RADIO_BUTTON = "//label[text() = 'Specific people']";

  public CreateFolderModalWindow(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(CREATE_FOLDER_HEADER);
  }

  @Override
  public void confirm() {
    click(CREATE_BUTTON);
  }

  @Override
  public void cancel() {
    click(CANCEL_BUTTON);
  }

  public void inputFolderName(String name) {
    enter(name, into(FOLDER_NAME_FIELD));
  }

  public void chooseFolderFromList(String name) {
    click("//tr[contains(@id,'folder_picker_row')]//div[text()='" + name + "']");
  }

  public void setOnlyYouRadioButton() {
    click(ONLY_YOU_RADIO_BUTTON);
  }

  public void setSpecificPeopleRadioButton() {
    click(SPECIFIC_PEOPLE_RADIO_BUTTON);
  }
}