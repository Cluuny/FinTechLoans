package com.fintechloans.presenter;

import com.fintechloans.view.View;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fintechloans.model.services.ToolKit;

public class Presenter {
    private View view;
    private File path;
    private Gson mapper;
    private ToolKit toolkit;
    private FileReader reader;

    public Presenter() throws Exception {
        view = new View();
        mapper = new Gson();
        toolkit = new ToolKit();
        path = new File("src/main/java/com/fintechloans/data/users.json");
        reader = new FileReader(path);
    }

    public void run() {
        String menu = "Bienvenido!\nSeleccione una de las siguientes opciones:\n1. Registrarse\n2. Iniciar Sesión\n3. Salir";
        boolean flag = true;
        while (flag) {
            int opt = view.readInt(menu);
            switch (opt) {
                case 1:
                    try {
                        this.register();
                    } catch (Exception e) {
                        view.print("Ha ocurrido un error inesperado, porfavor intente de nuevo" + e.getStackTrace());
                    }
                    break;
                case 2:
                    toolkit.logUser();
                    this.runServices();
                    break;
                case 3:
                    view.print("Gracias por usar nuestros servicios, vuelva pronto!");
                    flag = false;
                    break;
                default:
                    view.print("opción no valida, intente nuevamente.");
                    break;
            }
        }
    }

    // TODO: Implementar registro de usuario
    public void register() throws IOException {
        String userOptions = "Porfavor escoga el tipo de cliente\n1. Cliente Regular\n2. Cliente Casino\n3. Regresar al menu principal";
        boolean flag = true;
        while (flag) {
            int userOpt = view.readInt(userOptions);
            switch (userOpt) {
                case 1:
                    String name = view.readString("Porfavor ingrese su nombre completo");
                    String email = view.readString("Porfavor ingrese su correo electronico");
                    int age = view.readInt("Porfavor ingrese su edad");
                    String contractType = view.readString("Porfavor ingrese su tipo de contrato");
                    int income = view.readInt("Porfavor ingrese su ingreso mensual");
                    int debts = view.readInt("Porfavor ingrese la cantidad de deudas que tiene");
                    try {
                        toolkit.createRegularUSer(name, email, age, income, contractType, debts);
                    } catch (Exception e) {
                        view.print(e.getLocalizedMessage() + "");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    view.print("Opción no valida, porfavor intente de nuevo");
                    userOpt = view.readInt(userOptions);
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
