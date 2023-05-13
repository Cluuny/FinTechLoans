package com.fintechloans.model.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class TesterJson {
  private String path = "src/main/java/com/fintechloans/data/users.json";
  private Gson jsontool;
  private FileReader reader;
  private FileWriter writer;
  private BufferedReader bufferedReader;

  public TesterJson() throws Exception {
    jsontool = new Gson();
    reader = new FileReader(path);
    // writer = new FileWriter(path);
    bufferedReader = new BufferedReader(reader);
  }

  public void test() {
    JsonElement obj = jsontool.fromJson(bufferedReader, JsonElement.class);
    JsonArray arr = obj.getAsJsonArray();
    System.out.println(arr + "");
  }

  public static void main(String[] args) {
    try {
      new TesterJson().test();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
