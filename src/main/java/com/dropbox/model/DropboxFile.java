package com.dropbox.model;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class DropboxFile {
  private String name;
  private String modified;
  private String members;
  private WebElement checkbox;

  private final String NAME_LOCATOR = ".//span[contains(@class,'file-name-element')]";
  private final String MODIFIED_LOCATOR = ".//td[contains(@class,'file-modified-at-cell')]//div[contains(@class,'mc-media-cell-text')]";
  private final String MEMBERS_LOCATOR = ".//td[contains(@class,'file-shared-with-cell')]//div[contains(@class,'audience-description')]";
  private final String CHECKBOX = ".//span[@class = 'mc-checkbox-border']";

  public DropboxFile(WebElement file) {
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
    try {
      this.checkbox = file.findElement(xpath(CHECKBOX));
    } catch (NoSuchElementException e) {
      this.checkbox = null;
    }
  }

  public WebElement getCheckboxLocator() {
   return checkbox;
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