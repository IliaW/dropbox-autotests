package com.dropbox.helpers;

import com.sun.glass.events.KeyEvent;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;

/**
 * Work with OS Windows.
 * Use the class if download button does not have 'input' attribute.
 */
public class KeyboardHelper {

  private Robot robot;

  public KeyboardHelper() {
    try {
      robot = new Robot();
      robot.setAutoDelay(500);
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  private void copyTextToClipboard(File file) {
    StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, stringSelection);
  }

  private void pasteTextFromClipboard() {
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
  }

  public void clickEnter() {
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  public void clickESC() {
    robot.keyPress(KeyEvent.VK_ESCAPE);
    robot.keyRelease(KeyEvent.VK_ESCAPE);
  }


  public void uploadFileFromWindowsOS(File file) {
    copyTextToClipboard(file);
    pasteTextFromClipboard();
    clickEnter();
    clickESC();
  }

  public void uploadFolderFromWindowsOS(File file) {
    copyTextToClipboard(file);
    pasteTextFromClipboard();
    clickEnter();
    clickEnter();
    // switchTo().alert don't work
    robot.keyPress(KeyEvent.VK_LEFT);
    robot.keyRelease(KeyEvent.VK_LEFT);
    clickEnter();
    clickESC();
  }
}