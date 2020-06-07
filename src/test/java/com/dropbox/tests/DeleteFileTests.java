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

  private int numberOfFilesBeforeDelete;

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
    numberOfFilesBeforeDelete = filesPage.getNumberOfFilesInList();
  }

  @Severity(SeverityLevel.CRITICAL)
  @Test(priority = 1)
  public void deleteAFewFiles() {
    filesPage.selectAllFilesWhichContainText("Aivazovsky");
    open.contextMenu().delete();
    deleteModalWindow.confirm();
    assertThat(filesPage.getNumberOfFilesInList()).isLessThan(numberOfFilesBeforeDelete);
  }

  @Severity(SeverityLevel.NORMAL)
  @Test(priority = 2)
  public void deleteAllFiles() {
    filesPage.selectAllFiles();
    open.contextMenu().delete();
    deleteModalWindow.confirm();
    assertThat(filesPage.getNumberOfFilesInList()).isEqualTo(0);
  }
}