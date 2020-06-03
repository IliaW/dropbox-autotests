package com.dropbox.tests;

import com.dropbox.Launcher;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.model.UserType.BASIC_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchTests extends Launcher {

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
      open.homePage();
  }

  @Test
  public void resultCounter() {
    search.byText("jpg");
    assertThat(search.counterReading()).isEqualTo(search.getCountOfFilesInList());
  }

  @Test
  public void checkTheResultsForComplianceWithTheRequest() {
    String searchRequest = "jpg";
    search.byText(searchRequest);
    assertThat(search.resultsContain(searchRequest)).isTrue();
  }

  @Test
  public void noResultsFound() {
    search.byText("&&&");
    assertThat(search.counterReading()).isEqualTo(0);
    assertThat(search.noResultsFoundDisplay()).isTrue();
  }
}