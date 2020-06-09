package com.dropbox.helpers;

import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.dropbox.App.driver;

public class ScreenshotHelper {

   public static void takeScreenshot() {
      File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("src/main/resources/screenshots", "Screen_" + System.currentTimeMillis() + ".png");
      try {
         Files.copy(tempFile, screen);
         saveScreenshot(screen);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   // Adding screenshots to allure report.
   @Attachment(value = "Page screenshot", type = "image/png")
   private static byte[] saveScreenshot(File file) throws IOException {
      return Files.toByteArray(file);
   }
}