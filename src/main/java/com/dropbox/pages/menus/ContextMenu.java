package com.dropbox.pages.menus;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContextMenu extends BasePage implements Menu {

  private final String CONTEXT_MENU_BUTTON = "//*[@class = 'primary-action-menu__buttons']//*[@class='mc-popover']";
  private final String STAR_BUTTON = "//*[@class = 'mc-popover-content-menu']//*[text() = 'Star']";
  private final String RENAME_BUTTON = "//*[@class = 'mc-popover-content-menu']//*[text() = 'Rename']";
  private final String DELETE_BUTTON = "//*[@class = 'mc-popover-content-menu']//*[text() = 'Delete']";

  public ContextMenu(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(STAR_BUTTON) && isDisplayed(DELETE_BUTTON);
  }

  @Override
  public ContextMenu openMenu() {
    click(CONTEXT_MENU_BUTTON);
    return this;
  }

  public void setStar() {
    click(STAR_BUTTON);
  }

  public void rename() {
    click(RENAME_BUTTON);
  }

  public void delete() {
    click(DELETE_BUTTON);
  }
}