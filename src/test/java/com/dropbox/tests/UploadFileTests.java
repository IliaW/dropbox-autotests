package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.data.FilesData.*;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class UploadFileTests extends Launcher {

  int numberOfFilesBeforeUpload;

  @BeforeClass
  public void setUpBeforeClass() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInAs(BASIC_USER_VOVK_ILLIA);
    }
  }

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!filesPage.isLoaded()) {
      open.filesPage();
    }
    numberOfFilesBeforeUpload = filesPage.getNumberOfAllFilesOnPage();
  }

  @Test
  @Epic("Smoke tests")
  @Feature("Upload files")
  @Story("Upload JPG picture")
  @Severity(SeverityLevel.CRITICAL)
  public void uploadPictureJPG() {
    secondaryActionMenu.clickUploadFilesButton();
    filesPage.uploadFile(AIVAZOVSKY_THE_NINTH_WAVE_JPG);
    assertThat(filesPage.getNumberOfAllFilesOnPage() - 1).isEqualTo(numberOfFilesBeforeUpload);
  }

  @Test
  @Epic("Smoke tests")
  @Feature("Upload files")
  @Story("Upload PDF file")
  @Severity(SeverityLevel.CRITICAL)
  public void uploadFilePDF() {
    secondaryActionMenu.clickUploadFilesButton();
    filesPage.uploadFile(HOW_GOOGLE_TESTS_PDF);
    assertThat(filesPage.getNumberOfAllFilesOnPage() - 1).isEqualTo(numberOfFilesBeforeUpload);
  }

  @Test
  @Epic("Smoke tests")
  @Feature("Upload files")
  @Story("Upload MP4 file")
  @Severity(SeverityLevel.CRITICAL)
  public void uploadVideoMP4() {
    secondaryActionMenu.clickUploadFilesButton();
    filesPage.uploadFile(SURPRISED_KITTY_MP4);
    assertThat(filesPage.getNumberOfAllFilesOnPage() - 1).isEqualTo(numberOfFilesBeforeUpload);
  }

  @Test
  @Epic("Smoke tests")
  @Feature("Upload files")
  @Story("Upload empty folder")
  @Severity(SeverityLevel.CRITICAL)
  public void uploadEmptyFolder() {
    secondaryActionMenu.clickUploadFolderButton();
    filesPage.uploadFolder(EMPTY_FOLDER);
    assertThat(filesPage.getNumberOfAllFilesOnPage()).isEqualTo(numberOfFilesBeforeUpload);
  }

  @Test
  @Epic("Smoke tests")
  @Feature("Upload files")
  @Story("Upload folder with JPG files")
  @Severity(SeverityLevel.CRITICAL)
  public void uploadFolder() {
    secondaryActionMenu.clickUploadFolderButton();
    filesPage.uploadFolder(FOLDER_WITH_3_JPG_PICTURES);
    assertThat(filesPage.getNumberOfAllFilesOnPage() - 1).isEqualTo(numberOfFilesBeforeUpload);
  }
}