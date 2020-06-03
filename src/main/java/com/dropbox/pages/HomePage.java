package com.dropbox.pages;

import com.dropbox.pages.menus.AccountMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

  public final String URL = "https://www.dropbox.com/h";

  private final String HOME_HEADER = "//h1[text() = 'Home']";
  private final String HOME_TITLE_TEXT = "Home – Dropbox";

  public AccountMenu accountMenu;

  public HomePage(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
    accountMenu = new AccountMenu(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    setExplicitWaitBySeconds(10);
    boolean status = isDisplayed(HOME_HEADER) & wd.getTitle().equals(HOME_TITLE_TEXT);
    setExplicitWaitBySeconds(4);
    return status;
  }
}