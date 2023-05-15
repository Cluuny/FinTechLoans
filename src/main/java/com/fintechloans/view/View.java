package com.fintechloans.view;

import java.util.Scanner;

public class View {
  private Scanner sc;

  // public View() {
  // // sc = new Scanner(System.in);
  // }

  public int readInt(String message) {
    System.out.println(message);
    int input = 0;
    try {
      sc = new Scanner(System.in);
      input = sc.nextInt();
      return input;
    } catch (Exception e) {
      System.out.println("Error de tipo: " + e.getMessage() + " porfavor ingrese un valor numérico valido.");
      return this.readInt(message);
    }
  }

  public String readString(String message) {
    System.out.println(message);
    String input = "";
    try {
      sc = new Scanner(System.in);
      input = sc.nextLine();
      return input;
    } catch (Exception e) {
      System.out.println("Error de tipo: " + e.getMessage() + " porfavor ingrese una cadena de caracteres valida.");
      return this.readString(message);
    }
  }

  public void print(String message) {
    System.out.println(message);
  }
}
