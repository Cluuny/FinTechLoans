package com.fintechloans.presenter;

import com.fintechloans.model.*;
import com.fintechloans.model.services.ToolKit;
import com.fintechloans.model.user.User;
import com.fintechloans.view.View;

public class PresenterTest {
  View view;
  ToolKit toolkit;

  public PresenterTest() throws Exception {
    view = new View();
    toolkit = new ToolKit();
  }

  public void run() {
    int userType = view.readInt("Ingrese una opcion:\n1. Cliente Regular\n2. Cliente Casino");
    String userEmail = view.readString("Ingrese su correo electronico");
    String userPassword = view.readString("Ingrese su contraseña");
    try {
      User loggedUser = toolkit.logUser(userEmail, userPassword, userType);
      view.print(toolkit.calculateRisk(loggedUser) + "");
      ;
    } catch (Exception e) {
      view.print("Ha ocurrido un error inesperado, porfavor intente de nuevo" + e.getStackTrace()
          + "\n" + e.getLocalizedMessage());
    }
  }

  public static void main(String[] args) {
    try {
      new PresenterTest().run();
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
