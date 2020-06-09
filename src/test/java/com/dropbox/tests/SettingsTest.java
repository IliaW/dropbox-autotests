package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SettingsTest extends Launcher {

   @DataProvider
   public static Object[][] accountNames() {
      return new Object[][]{
              {}
      };
   }

   @Severity(SeverityLevel.NORMAL)
   @Test
   public void addAccountPhoto() {

   }

   @Severity(SeverityLevel.NORMAL)
   @Test(dataProvider = "accountNames")
   public void changeAccountName(String firstName, String lastName) {

   }
}