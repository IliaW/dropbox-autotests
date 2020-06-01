package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SharedPage extends BasePage {

  public final String URL = "https://www.dropbox.com/share";

  private final String SHARED_TITLE_TEXT = "Shared – Dropbox";
  private final String SHARED_HEADER = "//h1/span[text() = 'Shared']";

  public SharedPage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(SHARED_HEADER) && wd.getTitle().equals(SHARED_TITLE_TEXT);
  }
}