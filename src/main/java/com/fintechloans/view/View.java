package com.fintechloans.view;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View {
  private Scanner sc;

  // public View() {
  // // sc = new Scanner(System.in);
  // }

  public int readInt(String message) {
    System.out.println(message);
    try {
      sc = new Scanner(System.in);
      return sc.nextInt();
    } catch (Exception e) {
      System.out.println("Error de tipo: " + e.getMessage() + " porfavor ingrese un valor numérico valido.");
      this.readInt(message);
    }
    return 0;
  }

  public String readString(String message) {
    System.out.println(message);
    try {
      sc = new Scanner(System.in);
      return sc.nextLine();
    } catch (Exception e) {
      System.out.println("Error de tipo: " + e.getMessage() + " porfavor ingrese una cadena de caracteres valida.");
      this.readString(message);
    }
    return "";
  }

  public void print(String message) {
    System.out.println(message);
  }
}
