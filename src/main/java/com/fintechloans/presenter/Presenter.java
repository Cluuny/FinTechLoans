package com.fintechloans.presenter;

import com.fintechloans.view.View;
import java.io.IOException;
import java.util.ArrayList;

import com.fintechloans.model.services.ToolKit;

public class Presenter {
    private View view;
    private ToolKit toolkit;

    public Presenter() throws Exception {
        view = new View();
        toolkit = new ToolKit();
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
        String name;
        String email;
        String password;
        int age;
        String contractType;
        int income;
        int debts;
        boolean flag = true;
        while (flag) {
            int userOpt = view.readInt(userOptions);
            switch (userOpt) {
                case 1:
                    view.print("Creación de usuario regular");
                    name = view.readString("Porfavor ingrese su nombre completo");
                    email = view.readString("Porfavor ingrese su correo electronico");
                    password = view.readString("ingrese una contraseña para su cuenta");
                    age = view.readInt("Porfavor ingrese su edad");
                    contractType = view.readString("Porfavor ingrese su tipo de contrato");
                    income = view.readInt("Porfavor ingrese su salario mensual");
                    debts = view.readInt("Porfavor ingrese el monto monetario de deudas que tiene: ");
                    try {
                        toolkit.createRegularUSer(name, email, password, age, income, contractType, debts);
                    } catch (Exception e) {
                        view.print(e.getLocalizedMessage() + "");
                    }
                    break;
                case 2:
                    name = view.readString("Porfavor ingrese su nombre completo");
                    email = view.readString("Porfavor ingrese su correo electronico");
                    password = view.readString("Ingrese una contraseña para su cuenta:");
                    age = view.readInt("Porfavor ingrese su edad");
                    contractType = view.readString("Porfavor ingrese su tipo de contrato");
                    income = view.readInt("Porfavor ingrese su salario mensual");
                    debts = view.readInt("Porfavor ingrese el monto monetario de sus deudas: ");
                    String hasGameStast = view.readString("Tiene usted un historial de juego? (y/n)").toLowerCase();
                    ArrayList<Integer> gameStats = new ArrayList<Integer>();
                    if (hasGameStast.equals("y")) {
                        Boolean exit = false;
                        view.print(
                                "Acontinuación ingrese uno a uno el puntaje obtenido por cada juego, cuando hay terminado ingrese la palabra: SALIR");
                        int gameCounter = 0;
                        while (!exit) {
                            String gameStat = view
                                    .readString("Ingrese el puntaje obtenido en su juego N°" + gameCounter + ": ")
                                    .toLowerCase();
                            if (gameStat.equals("salir")) {
                                exit = true;
                            } else {
                                gameStats.add(Integer.parseInt(gameStat));
                            }
                            gameCounter += 1;
                        }
                    }
                    try {
                        toolkit.createCasinoUser(name, email, password, age, income, contractType, debts, gameStats);
                    } catch (Exception e) {
                        view.print(e.getLocalizedMessage() + "");
                    }
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    view.print("Opción no valida, porfavor intente de nuevo");
                    this.register();
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
