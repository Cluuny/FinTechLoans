package com.fintechloans.view;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View {
  private Scanner sc;

  public View() {
    sc = new Scanner(System.in);
  }

  public int readInt(String message) {
    System.out.println(message);
    return sc.nextInt();
  }

  public String readString(String message) {
    System.out.println(message);
    return sc.next();
  }

  public void print(String message) {
    System.out.println(message);
  }
}
