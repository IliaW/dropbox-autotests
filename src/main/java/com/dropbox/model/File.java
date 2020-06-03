package com.dropbox.model;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class File {
  private String name;
  private String modified;
  private String members;

  private final String NAME_LOCATOR = ".//span[contains(@class,'file-name-element')]";
  private final String MODIFIED_LOCATOR = ".//td[contains(@class,'file-modified-at-cell')]//div[contains(@class,'mc-media-cell-text')]";
  private final String MEMBERS_LOCATOR = ".//td[contains(@class,'file-shared-with-cell')]//div[contains(@class,'audience-description')]";

  public File(WebElement file) {
    this.name = file.findElement(xpath(NAME_LOCATOR)).getText();
    try {
      this.modified = file.findElement(xpath(MODIFIED_LOCATOR)).getText();
    } catch (NoSuchElementException e) {
      this.modified = null;
    }
    try {
      this.members = file.findElement(xpath(MEMBERS_LOCATOR)).getText();
    } catch (NoSuchElementException e) {
      this.members = null;
    }
  }

  public String getName() {
    return name;
  }

  public String getModified() {
    return modified;
  }

  public String getMembers() {
    return members;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public void setMembers(String members) {
    this.members = members;
  }
}