package com.dropbox.pages.menus;

import com.dropbox.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewFileMenu extends BasePage {

   private final String CREATE_NEW_FILE_BUTTON = "//span[text() = 'Create new file']";
   private final String DROPBOX_PAPER_BUTTON = "//span[text() = 'Dropbox Paper']";
   private final String DROPBOX_PAPER_TEMPLATE_BUTTON = "//span[text() = 'Dropbox Paper template']";
   private final String SHORTCUT_BUTTON = "//span[text() = 'Shortcut']";
   private final String WORD_DOCUMENT_BUTTON = "//span[text() = 'Word document']";
   private final String EXCEL_WORKBOOK_BUTTON = "//span[text() = 'Excel workbook']";
   private final String POWERPOINT_PRESENTATION_BUTTON = "//span[text() = 'PowerPoint presentation']";
   private final String GOOGLE_DOCS_BUTTON = "//span[text() = 'Google Docs']";
   private final String GOOGLE_SHEETS_BUTTON = "//span[text() = 'Google Sheets']";
   private final String GOOGLE_SLIDES_BUTTON = "//span[text() = 'Google Slides']";

   public CreateNewFileMenu(WebDriver wd, WebDriverWait wait) {
      super(wd, wait);
   }

   @Override
   public boolean isLoaded() {
      return isDisplayed(WORD_DOCUMENT_BUTTON) & isDisplayed(GOOGLE_DOCS_BUTTON);
   }

   public CreateNewFileMenu openMenu() {
      click(CREATE_NEW_FILE_BUTTON);
      return this;
   }

   public void dropboxPaper() {
      click(DROPBOX_PAPER_BUTTON);
   }

   public void dropboxPaperTemplate() {
      click(DROPBOX_PAPER_TEMPLATE_BUTTON);
   }

   public void shortcut() {
      click(SHORTCUT_BUTTON);
   }

   public void wordDocument() {
      click(WORD_DOCUMENT_BUTTON);
   }

   public void excelPresentation() {
      click(EXCEL_WORKBOOK_BUTTON);
   }

   public void powerpointPresentation() {
      click(POWERPOINT_PRESENTATION_BUTTON);
   }

   public void googleDocs() {
      click(GOOGLE_DOCS_BUTTON);
   }

   public void googleSheets() {
      click(GOOGLE_SHEETS_BUTTON);
   }

   public void googleSlides() {
      click(GOOGLE_SLIDES_BUTTON);
   }
}