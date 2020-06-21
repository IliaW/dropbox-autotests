package com.dropbox.model;

import org.openqa.selenium.Cookie;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class User {

   private String name;
   private String login;
   private String pass;
   private File cookieFile;

   private final String fileFormat = ".txt";

   private final String pathToFile = "src/main/resources/";

   public User(String login, String pass) {
      this.name = "NoName_" + (int) (Math.random() * 9999);
      this.login = login;
      this.pass = pass;
      this.cookieFile = new File(pathToFile + name + fileFormat);
   }

   public User(String name, String login, String pass) {
      this.name = name;
      this.login = login;
      this.pass = pass;
      this.cookieFile = new File(pathToFile + name + fileFormat);
   }

   public String getLogin() {
      return login;
   }

   public String getPass() {
      return pass;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean cookiesIsExist() {
      return cookieFile.exists();
   }

   public Set<Cookie> getSavedCookies() throws IOException {
      Set<Cookie> cookiesList = new HashSet<>();
      FileReader fileReader = new FileReader(cookieFile.getAbsolutePath());
      Scanner scan = new Scanner(fileReader);
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         String[] list = line.split(": ");
         if (list.length == 2) {
            cookiesList.add(new Cookie(list[0], list[1]));
         }
      }
      fileReader.close();
      return cookiesList;
   }

   public void saveCookies(Set<Cookie> cookies) throws IOException {
      FileWriter fileWriter = new FileWriter(pathToFile + name + fileFormat);
      for (Cookie c : cookies) {
         fileWriter.write(c.getName() + ": " + c.getValue() + "\n");
      }
      fileWriter.close();
   }

   public void deleteCookiesFile() {
      if (cookieFile.delete()) {
         System.out.println("File " + cookieFile.getName() + " deleted.");
      } else {
         System.out.println("Oops. File" + cookieFile.getName() + " was not deleted.");
      }
   }

   @Override
   public String toString() {
      return "User{" +
              "name='" + name + '\'' +
              ", login='" + login + '\'' +
              ", pass='" + pass + '\'' +
              '}';
   }
}