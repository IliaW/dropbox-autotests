package com.dropbox.helpers;

import com.dropbox.pages.menus.AccountMenu;
import com.dropbox.pages.menus.ContextMenu;
import com.dropbox.pages.menus.CreateNewFileMenu;

import static com.dropbox.App.*;

public class NavigationHelper {

  private void openURL(String URL) {
    driver.get(URL);
  }

  // Pages
  public void signInPage() {
    openURL(signInPage.URL);
    signInPage.isLoaded();
  }

  public void homePage() {
    openURL(homePage.URL);
    homePage.isLoaded();
  }

  public void filesPage() {
    openURL(filesPage.URL);
    filesPage.isLoaded();
  }

  public void sharedPage() {
    openURL(sharedPage.URL);
    sharedPage.isLoaded();
  }

  public void deletedFilesPage() {
    openURL(deletedFilesPage.URL);
    deletedFilesPage.isLoaded();
  }

  public void settingsPage() {
    openURL(settingsPage.URL);
    settingsPage.isLoaded();
  }

  // Menus
  public AccountMenu accountMenu(){
    accountMenu.openMenu().isLoaded();
    return accountMenu;
  }

  public ContextMenu contextMenu(){
    contextMenu.openMenu().isLoaded();
    return contextMenu;
  }

  public CreateNewFileMenu createNewFileMenu(){
    createNewFileMenu.openMenu().isLoaded();
    return createNewFileMenu;
  }
}