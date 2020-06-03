package com.dropbox.tests;

import com.dropbox.Launcher;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.data.FilesData.*;
import static com.dropbox.model.UserType.BASIC_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteFileTests extends Launcher {

  int countOfFilesBeforeDelete;

  @BeforeClass
  public void setUpBeforeClass() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInAs(BASIC_USER);
      assertThat(homePage.isLoaded()).isTrue();
    }
    open.filesPage();
    filesPage.uploadFiles(AIVAZOVSKY_THE_NINTH_WAVE_JPG, AIVAZOVSKY_CHAOS_JPG, SURPRISED_KITTY_MP4);
  }

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!filesPage.isLoaded()) {
      open.filesPage();
    }
    countOfFilesBeforeDelete = filesPage.getCountOfFilesInList();
  }

  @AfterMethod
  public void tearDownAfterTest() {
    countOfFilesBeforeDelete = 0;
  }

  @Test(priority = 1)
  public void deleteAFewFiles() {
    filesPage.selectAllFilesWhichContainText("Aivazovsky");
    open.contextMenu().delete();
    deleteMW.confirm();
    assertThat(countOfFilesBeforeDelete).isLessThan(filesPage.getCountOfFilesInList());
  }

  @Test(priority = 2)
  public void deleteAllFiles() {
    filesPage.selectAllFiles();
    open.contextMenu().delete();
    deleteMW.confirm();
    assertThat(filesPage.getCountOfFilesInList()).isEqualTo(0);
  }
}