package com.dropbox.pages.menus;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountMenu extends BasePage implements Menu {

  private final String ACCOUNT_MENU_BUTTON = "//button[@aria-label= 'Account menu']";
  private final String ADD_PHOTO_BUTTON = "//button[@class='account-menu-avatar-link ']";
  private final String UPGRADE_BUTTON = "//*[@class='account-menu-upgrade-link']";
  private final String SETTINGS_BUTTON = "//*[text() = 'Settings']";
  private final String INSTALL_BUTTON = "//*[text() = 'Install']";
  private final String SIGN_OUT_BUTTON = "//*[text() = 'Sign out']";

  public AccountMenu(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(SETTINGS_BUTTON) && isDisplayed(SIGN_OUT_BUTTON);
  }

  @Override
  public AccountMenu openMenu() {
    click(ACCOUNT_MENU_BUTTON);
    return this;
  }

  public boolean isUserAuthorized(){
    return isDisplayed(ACCOUNT_MENU_BUTTON);
  }

  public void addPhoto() {
    click(ADD_PHOTO_BUTTON);
  }

  public void upgrade() {
    click(UPGRADE_BUTTON);
  }

  public void settings() {
    click(SETTINGS_BUTTON);
  }

  public void install() {
    click(INSTALL_BUTTON);
  }

  public void signOut() {
    click(SIGN_OUT_BUTTON);
  }
}