package com.dropbox.model;

import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class File {
  private String name;
  private String modified;
  private String members;

  public File(WebElement file) {
    this.name = file.findElement(xpath(".//span[contains(@class,'file-name-element')]")).getText();
    this.modified = file.findElement
            (xpath(".//td[contains(@class,'file-modified-at-cell')]//div[contains(@class,'mc-media-cell-text')]"))
            .getText();
    this.members = file.findElement
            (xpath(".//td[contains(@class,'file-shared-with-cell')]//div[contains(@class,'audience-description')]"))
            .getText();
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
}
