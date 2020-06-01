package com.dropbox.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertHelper {

  private final WebDriver driver;
  private final WebDriverWait wait;

  public AlertHelper(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public boolean isDisplay() {
    try {
      wait.until(ExpectedConditions.alertIsPresent());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void accept() {
    try {
      driver.switchTo().alert().accept();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void dismiss() {
    try {
      driver.switchTo().alert().dismiss();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}