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
public class KeyEventHelper {

  private Robot robot;

  public KeyEventHelper() {
    try {
      robot = new Robot();
      robot.setAutoDelay(100);
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  private void copyPathToClipboard(File file) {
    StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, stringSelection);
  }

  private void insertTextFromClipboard() {
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
  }

  private void clickEnter() {
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  public void uploadFileFromWindowsOS(File file) {
    copyPathToClipboard(file);
    insertTextFromClipboard();
    clickEnter();

  }

  public void uploadFolderFromWindowsOS(File file) {
    copyPathToClipboard(file);
    insertTextFromClipboard();
    clickEnter();
    clickEnter();
    // switchTo().alert don't work
    robot.keyPress(KeyEvent.VK_LEFT);
    robot.keyPress(KeyEvent.VK_LEFT);
    clickEnter();
  }

}