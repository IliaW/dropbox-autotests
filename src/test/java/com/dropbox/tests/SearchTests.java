package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.dropbox.App.*;
import static com.dropbox.helpers.ScreenshotHelper.takeScreenshot;
import static com.dropbox.model.UserType.BASIC_USER_VOVK_ILLIA;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchTests extends Launcher {

   @BeforeMethod
   public void setUpBeforeTest() {
      if (!accountMenu.isUserAuthorized()) {
         open.signInPage().signInAs(BASIC_USER_VOVK_ILLIA);
         signInPage.isLoaded();
      }
      open.homePage();
   }

   @AfterMethod
   public void tearDownAfterMethod(){
      takeScreenshot();
   }

   @Test
   @Flaky // When searching, sometimes system logout.
   @Feature("Search")
   @Severity(SeverityLevel.MINOR)
   public void resultCounter() {
      search.byText("jpg");
      assertThat(search.counterOfSearchResults()).isEqualTo(search.getNumberOfAllFilesOnPage());
   }

   @Test
   @Feature("Search")
   @Severity(SeverityLevel.NORMAL)
   public void checkResultsForComplianceWithRequest() {
      String searchRequest = "jpg";
      search.byText(searchRequest);
      assertThat(search.resultsContain(searchRequest)).isTrue();
   }

   @Test
   @Feature("Search")
   @Severity(SeverityLevel.NORMAL)
   public void noResultsFound() {
      search.byText("&&&");
      assertThat(search.counterOfSearchResults()).isEqualTo(0);
      assertThat(search.noResultsFoundDisplay()).isTrue();
   }
}