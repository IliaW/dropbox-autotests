package com.dropbox;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


public class Launcher {

   public static final App app = new App();

   @Parameters("browser")
   @BeforeSuite
   public void setUp(String browser) {
      app.init(browser);
   }

// Use for debug
//   @BeforeSuite
//   public void setUp() {
//      app.init("chrome");
//   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() {
      app.stop();
   }
}