package com.dropbox.pages.other;

import com.dropbox.helpers.KeyEventHelper;
import com.dropbox.model.File;
import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Search extends BasePage {

  KeyEventHelper keyEventHelper;

  private final String SEARCH_VIEW = "//div[@id='search-view']";
  private final String SEARCH_FIELD = "//input[contains(@aria-label,'Search in folder')]";
  private final String X_BUTTON = "//button[@aria-label = 'Exit search']";
  private final String COUNT_OF_RESULTS = "//h1[contains(text(),'result')]";
  private final String NO_RESULTS_FOUND = "//h2[text() = 'No results found']";

  public Search(WebDriver wd, WebDriverWait wait) {
    super(wd, wait);
    keyEventHelper = new KeyEventHelper();
  }

  @Override
  public boolean isLoaded() {
    return isDisplayed(X_BUTTON) & isDisplayed(SEARCH_VIEW);
  }

  // search by text
  public void byText(String text) {
    click(SEARCH_FIELD);
    this.isLoaded();
    enter(text, into(SEARCH_FIELD));
    keyEventHelper.clickEnter();
    waitFor(3000);
  }

  public int counterReading() {
    String[] array = find(COUNT_OF_RESULTS).getText().split(" ");
    return parseInt(array[0]);
  }

  // Check
  public boolean noResultsFoundDisplay() {
    return isDisplayed(NO_RESULTS_FOUND);
  }

  public boolean resultsContain(String request) {
    setImplicitWaitBySeconds(0);
    List<File> files = getListOfAllFilesOnPageWithAttributes();
    setImplicitWaitBySeconds(4);
    for (File resultOfSearch : files) {
      if(!resultOfSearch.getName().toLowerCase().contains(request.toLowerCase())){
        return false;
      }
    }
    return true;
  }
}