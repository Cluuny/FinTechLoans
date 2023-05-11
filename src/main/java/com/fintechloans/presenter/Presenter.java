package com.fintechloans.presenter;

import com.fintechloans.view.View;
import com.fintechloans.model.services.ToolKit;

public class Presenter {
    private View view;
    private ToolKit toolkit;

    public Presenter() {
        view = new View();
        toolkit = new ToolKit();
    }

    public void run() {
        String menu = "Bienvenido!\nSeleccione una de las siguientes opciones:\n1. Registrarse\n2. Iniciar Sesión\n3. Salir";
        int opt = view.readInt(menu);
        while (opt <= 3) {
            switch (opt) {
                case 1:
                    toolkit.createUser();
                    runServices();
                    break;
                case 2:
                    toolkit.logUser();
                    runServices();
                    break;
                case 3:
                    opt = 4;
                    break;
            }
        }
    }

    public void runServices() {
        String runServicesMenu = "Hemos verificado tu identidad!\nAhora porfavor escoge que quieres hacer hoy?\n1. Adquirir productos\n2. Añadir metodos de pago\n3. Pagar productos pendientes\n4. Regresa al menu principal";
        int runServicesOpt = view.readInt(runServicesMenu);
        while (runServicesOpt <= 4) {
            switch (runServicesOpt) {
                case 1:
                    buyProducts();
                    break;
                case 2:
                    // Implementacion de metodos de pago
                    break;
                case 3:
                    // Implementacion de pago de prestamos
                    break;
                case 4:
                    runServicesOpt = 5;
                    break;
            }
        }
    }

    public void buyProducts() {
        // Unimplemented
    }

    public static void main(String[] args) throws Exception {
        new Presenter().run();
    }
}
