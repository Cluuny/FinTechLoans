package com.fintechloans.exceptions;

public class UserNotFoundException extends Exception {
  public UserNotFoundException() {
    super("Usuario no encontrado");
  }
}
