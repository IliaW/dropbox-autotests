package com.dropbox.tests;

import com.dropbox.Launcher;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.dropbox.App.open;

public class MoveFileTests extends Launcher {

   @Test
   @Feature("Move file")
   @Severity(SeverityLevel.MINOR)
   public void dragAndDropFileToFolder() {
      open.filesPage();
      //TODO

   }
}