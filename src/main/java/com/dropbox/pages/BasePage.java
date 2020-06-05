package com.dropbox.pages;

import com.dropbox.model.DropboxFile;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {

  protected WebDriver wd;
  protected WebDriverWait wait;
  protected final Actions actions;
  protected final int DEFAULT_IMPLICIT_WAIT = 4;
  protected final int DEFAULT_EXPLICIT_WAIT = 4;

  private final String FILE_ROW = "//tbody[contains(@class,'table-body mc-table-body-culled')]//tr";

  public BasePage(WebDriver wd, WebDriverWait wait) {
    this.wd = wd;
    this.wait = wait;
    actions = new Actions(wd);
  }

  // Abstract
  public abstract boolean isLoaded();

  // Methods
  public WebElement find(String locator) {
    return wd.findElement(xpath(locator));
  }

  public List<WebElement> findAll(String locator) {
    return wd.findElements(xpath(locator));
  }

  public void click(String locator) {
    find(locator).click();
  }

  public void doubleClick(String locator) {
    actions.doubleClick(find(locator)).perform();
  }

  public void enter(String text, WebElement field) {
    field.sendKeys(text);
  }

  public WebElement into(String locator) {
    WebElement field = find(locator);
    field.clear();
    return field;
  }

  public void setCheckbox(String locator) {
    if (!find(locator).isSelected()) {
      click(locator);
    }
  }

  public void resetCheckbox(String locator) {
    if (find(locator).isSelected()) {
      click(locator);
    }
  }

  public void refreshPage() {
    wd.navigate().refresh();
  }

  public List<DropboxFile> getListOfAllFilesOnPage() {
    List<WebElement> listOfWebElements = findAll(FILE_ROW);
    List<DropboxFile> filesList = new ArrayList<>();
    if (listOfWebElements.size() != 0) {
      for (WebElement file : listOfWebElements) {
        filesList.add(new DropboxFile(file));
      }
    }
    return filesList;
  }

  public int getCountOfFilesInList() {
    return findAll(FILE_ROW).size();
  }

  public void actionExecution(String processing, String finish) {
    if (isDisplayed(processing)) {
      setExplicitWaitBySeconds(15);
      isDisplayed(finish);
      setExplicitWaitBySeconds(DEFAULT_IMPLICIT_WAIT);
    }
  }

  // Wait
  public void setImplicitWaitBySeconds(int seconds) {
    wd.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
  }

  public void setExplicitWaitBySeconds(int seconds) {
    wait.withTimeout(Duration.ofSeconds(seconds));
  }

  // Checks
  public boolean isDisplayed(String locator) {
    try {
      wait.until(visibilityOfElementLocated(xpath(locator)));
      return true;
    } catch (ElementNotVisibleException | TimeoutException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static void waitFor(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}