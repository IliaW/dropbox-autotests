package com.dropbox;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class Launcher {

  public static final App app = new App();

  @BeforeSuite
  public void setUp() {
    app.init(CHROME);
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }
}