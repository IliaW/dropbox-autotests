package com.dropbox.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

  public final String URL = "https://www.dropbox.com/h";

  private final String HOME_HEADER = "//h1[text() = 'Home']";
  private final String HOME_TITLE_TEXT = "Home – Dropbox";

  public HomePage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    setExplicitWaitBySeconds(10);
    boolean result = isDisplayed(HOME_HEADER) & wd.getTitle().equals(HOME_TITLE_TEXT);
    setExplicitWaitBySeconds(DEFAULT_EXPLICIT_WAIT);
    return result;
  }
}