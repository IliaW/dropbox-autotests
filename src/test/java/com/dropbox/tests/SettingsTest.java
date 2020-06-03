package com.dropbox.tests;

import com.dropbox.Launcher;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SettingsTest extends Launcher {

  @DataProvider
  public static Object[][] accountNames() {
    return new Object[][]{
            {}
    };
  }

  @Test
  public void addAccountPhoto() {

  }

  @Test(dataProvider = "accountNames")
  public void changeAccountName(String firstName, String lastName) {

  }
}