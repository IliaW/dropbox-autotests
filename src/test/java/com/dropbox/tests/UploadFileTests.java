package com.dropbox.tests;

import com.dropbox.Launcher;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.data.FilesData.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Uploading:
 * JPEG picture (pass)
 * PDF file (pass)
 * MP4 video (pass)
 * empty folder (fail)
 * folder with files (pass)
 */

public class UploadFileTests extends Launcher {

  int countOfFilesBeforeUpload;

  @BeforeClass
  public void setUpBeforeClass() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInWithCookies();
      assertThat(homePage.isLoaded()).isTrue();
    }
  }

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!filesPage.isLoaded()) {
      open.filesPage();
    }
    countOfFilesBeforeUpload = filesPage.getCountOfFilesInList();
  }

  @AfterMethod
  public void tearDownAfterTest() {
    countOfFilesBeforeUpload = 0;
  }

  @Test
  public void uploadPictureJPEG() {
    filesPage.uploadFiles(AIVAZOVSKY_THE_NINTH_WAVE_JPG);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.getCountOfFilesInList() - 1);
  }

  @Test
  public void uploadFilePDF() {
    filesPage.uploadFiles(HOW_GOOGLE_TESTS_PDF);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.getCountOfFilesInList() - 1);
  }

  @Test
  public void uploadVideoMP4() {
    filesPage.uploadFiles(SURPRISED_KITTY_MP4);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.getCountOfFilesInList() - 1);
  }

  @Test
  public void uploadEmptyFolder() {
    filesPage.uploadFolder(EMPTY_FOLDER);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.getCountOfFilesInList() - 1);
  }

  @Test
  public void uploadFolder() {
    filesPage.uploadFolder(FOLDER_WITH_3_JPG_PICTURES);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.getCountOfFilesInList() - 1);
  }
}