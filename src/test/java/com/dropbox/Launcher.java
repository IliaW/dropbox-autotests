package com.dropbox;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class Launcher {

  public static final App app = new App();

  //@Parameters("browser")
  @BeforeSuite
  public void setUp() {
      app.init(CHROME);
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }
}