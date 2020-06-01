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

/**
 * Uploading:
 * JPEG picture
 * PDF file
 * MP4 video
 * empty folder
 * folder with files
 */

public class UploadFilesTest extends Launcher {

  int countOfFilesBeforeUpload;

  @BeforeClass
  public void setUpBeforeClass() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInAs(BASIC_USER);
      assertThat(homePage.isLoaded()).isTrue();
    }
  }

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!filesPage.isLoaded()) {
      open.filesPage();
    }
    countOfFilesBeforeUpload = filesPage.countOfUploadedFilesOnPage();
  }

  @AfterMethod
  public void tearDownAfterTest() {
    countOfFilesBeforeUpload = 0;
  }

  @Test
  public void uploadPictureJPEG() {
    filesPage.uploadFile(PICTURE_JPEG_1900X1200_300kB);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.countOfUploadedFilesOnPage() - 1);
  }

  @Test
  public void uploadFilePDF() {
    filesPage.uploadFile(DOCUMENT_PDF_7_51MB);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.countOfUploadedFilesOnPage() - 1);
  }

  @Test
  public void uploadVideoMP4() {
    filesPage.uploadFile(VIDEO_MP4_852kB);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.countOfUploadedFilesOnPage() - 1);
  }

  @Test
  public void uploadEmptyFolder() {
    filesPage.uploadFolder(EMPTY_FOLDER);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.countOfUploadedFilesOnPage() - 1);
  }

  @Test
  public void uploadFolder() {
    filesPage.uploadFolder(FOLDER_WITH_3_JPEG_PICTURES);
    assertThat(countOfFilesBeforeUpload).isEqualTo(filesPage.countOfUploadedFilesOnPage() - 1);
  }
}