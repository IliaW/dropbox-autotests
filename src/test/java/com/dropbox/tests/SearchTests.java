package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchTests extends Launcher {

  @BeforeMethod
  public void setUpBeforeTest() {
    if (!accountMenu.isUserAuthorized()) {
      open.signInPage();
      signInPage.signInAs(BASIC_USER_VOVK_ILLIA);
    }
    open.homePage();
  }

  // When searching, sometimes system logout.
  @Test
  @Severity(SeverityLevel.MINOR)
  @Flaky
  public void resultCounter() {
    search.byText("jpg");
    assertThat(search.counterOfSearchResults()).isEqualTo(search.getNumberOfAllFilesOnPage());
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  public void checkResultsForComplianceWithRequest() {
    String searchRequest = "jpg";
    search.byText(searchRequest);
    assertThat(search.resultsContain(searchRequest)).isTrue();
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  public void noResultsFound() {
    search.byText("&&&");
    assertThat(search.counterOfSearchResults()).isEqualTo(0);
    assertThat(search.noResultsFoundDisplay()).isTrue();
  }
}