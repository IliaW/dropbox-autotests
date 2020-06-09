package com.dropbox.helpers;

import com.dropbox.pages.*;
import com.dropbox.pages.menus.AccountMenu;
import com.dropbox.pages.menus.CreateNewFile;
import com.dropbox.pages.menus.PrimaryActionMenu;

import static com.dropbox.App.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NavigationHelper {

  private void openURL(String URL) {
    driver.get(URL);
  }

  /**
   * Opening and checks of loading pages
   */
  public SignInPage signInPage() {
    openURL(signInPage.URL);
    assertThat(signInPage.isLoaded()).isTrue();
    return signInPage;
  }

  public HomePage homePage() {
    openURL(homePage.URL);
    assertThat(homePage.isLoaded()).isTrue();
    return homePage;
  }

  public FilesPage filesPage() {
    openURL(filesPage.URL);
    assertThat(filesPage.isLoaded()).isTrue();
    return filesPage;
  }

  public DeletedFilesPage deletedFilesPage() {
    openURL(deletedFilesPage.URL);
    assertThat(deletedFilesPage.isLoaded()).isTrue();
    return deletedFilesPage;
  }

  public SettingsPage settingsPage() {
    openURL(settingsPage.URL);
    assertThat(settingsPage.isLoaded()).isTrue();
    return settingsPage;
  }

  /**
   * Opening and checks of loading menus
   */
  public AccountMenu accountMenu() {
    accountMenu.openMenu();
    assertThat(accountMenu.isLoaded()).isTrue();
    return accountMenu;
  }

  public PrimaryActionMenu contextMenu() {
    primaryActionMenu.openMenu();
    assertThat(primaryActionMenu.isLoaded()).isTrue();
    return primaryActionMenu;
  }

  public CreateNewFile createNewFileMenu() {
    createNewFile.openMenu();
    assertThat(createNewFile.isLoaded()).isTrue();
    return createNewFile;
  }
}