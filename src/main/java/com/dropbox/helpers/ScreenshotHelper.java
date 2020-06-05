package com.dropbox.helpers;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.dropbox.App.driver;

public class ScreenshotHelper {

  //TODO
//  public void screen() {
//    File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//    try {
//      Files.copy(tempFile, new File("screen.png"));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
}