package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteFileTests extends Launcher {

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
  }

  @Test(priority = 1)
  @Severity(SeverityLevel.CRITICAL)
  public void deleteAFewFiles() {
    filesPage.selectAllFilesWhichContainText("Aivazovsky");
    open.contextMenu().delete();
    deleteModalWindow.confirm();
    assertThat(filesPage.selectAllFilesWhichContainText("Aivazovsky")).isEqualTo(0);
  }

  @Test(priority = 2)
  @Severity(SeverityLevel.NORMAL)
  public void deleteAllFiles() {
    filesPage.selectAllFiles();
    open.contextMenu().delete();
    deleteModalWindow.confirm();
    assertThat(filesPage.getNumberOfAllFilesOnPage()).isEqualTo(0);
  }
}