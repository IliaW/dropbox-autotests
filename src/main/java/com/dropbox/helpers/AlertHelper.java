package com.dropbox.helpers;

import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.dropbox.App.driver;
import static com.dropbox.App.wait;

public class AlertHelper {

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