package com.fintechloans.presenter;

import com.fintechloans.view.View;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.fintechloans.model.product.Product;
import com.fintechloans.model.services.ToolKit;
import com.fintechloans.model.user.RegularCustomer;
import com.fintechloans.model.user.User;

public class Presenter {
    private View view;
    private ToolKit toolkit;

    private LocalDate actuLocalDate;

    public Presenter() throws Exception {
        view = new View();
        toolkit = new ToolKit();
        actuLocalDate = LocalDate.now();
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
                        view.print(
                                "Ha ocurrido un error inesperado, porfavor intente de nuevo\n" + e.getMessage() + "\n"
                                        + Arrays.toString(e.getStackTrace()));
                    }
                    break;
                case 2:
                    int userType = view.readInt("Ingrese una opcion:\n1. Cliente Regular\n2. Cliente Casino");
                    String userEmail = view.readString("Ingrese su correo electronico");
                    String userPassword = view.readString("Ingrese su contraseña");
                    try {
                        User loggedUser = toolkit.logUser(userEmail, userPassword, userType);
                        this.runServices(loggedUser);
                    } catch (Exception e) {
                        view.print(
                                "Ha ocurrido un error inesperado, porfavor intente de nuevo\n" + e.getMessage() + "\n"
                                        + e.getStackTrace() + "\n" + e.getLocalizedMessage() + "\n" + e.getCause()
                                        + "\n" + e.getClass());
                    }
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

    public void register() throws IOException {
        String userOptions = "Porfavor escoga el tipo de cliente\n1. Cliente Regular\n2. Cliente Casino\n3. Regresar al menu principal";
        int age;
        int debts;
        int income;
        String name;
        String email;
        String password;
        int spendAmount;
        int contractType;
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
                    contractType = view.readInt(
                            "Porfavor ingrese su tipo de contrato: \n1. Fromal - termino fijo\n2. Formal - termino indefinido\n3. Informal\n4. Desempleado");
                    income = view.readInt("Porfavor ingrese su salario mensual");
                    spendAmount = view.readInt(
                            "Porfavor ingrese el monto con el que cuenta para gestionar su cartera de prestamos: ");
                    debts = view.readInt("Porfavor ingrese el monto monetario de deudas que tiene: ");
                    try {
                        toolkit.createRegularUSer(name, email, password, age, income, spendAmount, contractType, debts);
                    } catch (Exception e) {
                        view.print(e.getLocalizedMessage() + "");
                    }
                    break;
                case 2:
                    name = view.readString("Porfavor ingrese su nombre completo");
                    email = view.readString("Porfavor ingrese su correo electronico");
                    password = view.readString("Ingrese una contraseña para su cuenta:");
                    age = view.readInt("Porfavor ingrese su edad");
                    contractType = view.readInt(
                            "Porfavor ingrese su tipo de contrato: \n1. Fromal - termino fijo\n2. Formal - termino indefinido\n3. Informal\n4. Desempleado");
                    income = view.readInt("Porfavor ingrese su salario mensual");
                    spendAmount = view.readInt(
                            "Porfavor ingrese el monto con el que cuenta para gestionar su cartera de prestamos: ");
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
                        toolkit.createCasinoUser(name, email, password, age, income, spendAmount, contractType, debts,
                                gameStats);
                    } catch (Exception e) {
                        view.print(e.getLocalizedMessage() + "");
                    }
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    view.print("Opción no valida, porfavor intente de nuevo");
                    break;
            }
        }

    }

    public void runServices(User user) {
        String runServicesMenu = "Bienvenido, " + user.getName()
                + "! \nHemos verificado tu identidad!\nAhora porfavor escoge que quieres hacer hoy?\n1. Adquirir productos\n2. Listar productos\n3. Pagar productos\n4. Cancelar productos\n5. Diferir producto\n6. Visitar mercados\n7. Consignar\n8. Cerrar sesión y regresar al menu principal";
        boolean flag = true;
        int productId;
        Product product;
        while (flag) {
            int runServicesOpt = view.readInt(runServicesMenu);
            switch (runServicesOpt) {
                case 1:
                    double amount = view.readDouble("Ingresa el monto para tu prestamo: ");
                    int term = view.readInt("Ingresa el numero de cuotas: ");
                    String responestoRequest = user.requestLoan(amount, term, LocalDate.now());
                    view.print(responestoRequest);
                    toolkit.updateJsonInfo(user);
                    break;
                case 2:
                    String responString = user.listProducts();
                    view.print(responString);
                    break;
                case 3:
                    if (user instanceof RegularCustomer) {
                        view.print("Fecha actual: " + actuLocalDate);
                        view.print("Simulando paso del tiempo...");
                        actuLocalDate = actuLocalDate.plusMonths(1);
                        view.print("Simulando completa." + "\n" + "Fecha actual: " + actuLocalDate);
                    }
                    productId = view.readInt("Ingresa el id del producto que deseas pagar: ");
                    product = toolkit.getProductById(productId, user);
                    String responseToPay = user.payLoanInstallment(actuLocalDate, product);
                    view.print(responseToPay);
                    toolkit.updateJsonInfo(user);
                    break;
                case 4:
                    productId = view.readInt("Ingresa el id del producto que deseas pagar: ");
                    product = toolkit.getProductById(productId, user);
                    String responseToCancel = user.cancelProduct(product);
                    view.print(responseToCancel);
                    toolkit.updateJsonInfo(user);
                    break;
                case 5:
                    productId = view.readInt("Ingresa el id del producto que deseas pagar: ");
                    product = toolkit.getProductById(productId, user);
                    int termToDiffer = view.readInt("Ingresa el numero de meses al que quieres diferir el prestamo: ");
                    String responseToDiffer = user.deferLoan(product, termToDiffer);
                    view.print(responseToDiffer);
                    toolkit.updateJsonInfo(user);
                    break;
                case 6:
                    // Implementacion de Visita de mercados
                    break;
                case 7:
                    int consigmentValue = view.readInt("Ingresa el valor a consignar: ");
                    user.conginment(consigmentValue);
                    toolkit.updateJsonInfo(user);
                    break;
                case 8:
                    flag = false;
                    break;
                default:
                    view.print("Opción no valida, porfavor intente de nuevo");
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Presenter().run();
    }
}
