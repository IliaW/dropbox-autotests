package com.dropbox.pages.other;

import com.dropbox.helpers.KeyboardHelper;
import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Integer.parseInt;

public class Search extends BasePage {

   private KeyboardHelper keyboardHelper;

   private final String SEARCH_VIEW = "//div[@id='search-view']";
   private final String SEARCH_FIELD = "//input[contains(@aria-label,'Search in folder')]";
   private final String X_BUTTON = "//button[@aria-label = 'Exit search']";
   private final String COUNT_OF_RESULTS = "//h1[contains(text(),'result')]";
   private final String NO_RESULTS_FOUND = "//h2[text() = 'No results found']";

   public Search(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
      keyboardHelper = new KeyboardHelper();
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(X_BUTTON) & isDisplayed(SEARCH_VIEW);
   }

   public void byText(String text) {
      click(SEARCH_FIELD);
      isLoaded();
      enter(text, into(SEARCH_FIELD));
      waitFor(1000);
      keyboardHelper.clickEnter();
      waitFor(2000);
   }

   public int counterOfSearchResults() {
      String[] array = find(COUNT_OF_RESULTS).getText().split(" ");
      return parseInt(array[0]);
   }

   // Check
   public boolean noResultsFoundDisplay() {
      return isDisplayed(NO_RESULTS_FOUND);
   }


}