package com.fintechloans.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fintechloans.model.product.Product;
import com.fintechloans.model.product.RegularLoan;

public abstract class User {
    /**
     * Atributos de la clase User
     *
     * @param name         Nombre del usuario
     * @param email        Correo electronico del usuario
     * @param password     Contraseña del usuario
     * @param age          Edad del usuario
     * @param income       Ingresos del usuario
     * @param spendAmount  Cartera disponiblde del usuario, a este valor se le
     *                     suman
     *                     los ingresos por prestamos y este es el valor que se
     *                     gasta
     *                     en los mercados
     * @param contractType Tipo de contrato del usuario:
     *                     1. Fromal - termino fijo
     *                     2. Formal - termino indefinido
     *                     3. Informal
     *                     4. Desempleado
     * @param debts        Deudas del usuario
     * @param score        Puntaje del usuario
     * @param products     Productos del usuario
     *
     */
    protected String name;
    protected String email;
    protected String password;
    protected int age;
    protected int income;
    protected int spendAmount;
    protected int contractType;
    protected int debts;
    protected int score;
    protected ArrayList<Product> products;

    public User(
            String name, String email,
            String password,
            int age,
            int income,
            int spendAmount,
            int contractType,
            int debts) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.income = income;
        this.spendAmount = spendAmount;
        this.contractType = contractType;
        this.debts = debts;
        this.score = this.calculateRisk();
        this.products = new ArrayList<Product>();
    }

    protected abstract int calculateRisk();

    /**
     * Metodo que permite al usuario solicitar un prestamo
     *
     * @param amount
     * @return Booelan
     */
    public abstract String requestLoan(double amount, int term, LocalDate generationDate);

    public String conginment(int amount) {
        this.spendAmount += amount;
        return "Su consignacion ha sido exitosa";
    };

    public String listProducts() {
        String stringProducts = "";
        for (Product product : this.products) {
            stringProducts += product.toString() + "\n\n";
        }
        return stringProducts;
    };

    /**
     * Metodo que permite al usuario pagar cuota de algun prestamo
     *
     * @param date
     * @param product
     * @return
     */
    public String payLoanInstallment(LocalDate date, Product product) {
        ArrayList<LocalDate> installments = (ArrayList<LocalDate>) product.getInstallments();
        double installmentAmount = product.getMonthlyPayment();
        String message;

        if (installments.contains(date)) {
            if (spendAmount >= installmentAmount) {
                message = "Pago correctamente recibido para la fecha: " + date;
                installments.remove(date);
                spendAmount -= installmentAmount;
                LocalDate nextInstallment = installments.get(0);
                product.setDueDate(nextInstallment);

                // El siguiente if else statement actualiza la variable remainingBalance
                // para poder manejar un método cancell con un balance para hacerle payoff al
                // loan
                double remainingBalance = product.getRemainingBalance();
                if (remainingBalance >= installmentAmount) {
                    remainingBalance -= installmentAmount;
                    product.setRemainingBalance(remainingBalance);
                } else {
                    product.setRemainingBalance(0);
                    product.setPaidOff(true);
                }
            } else {
                message = "Fondos insuficientes para pagar la cuota en la fecha: " + date;
            }
        } else {
            message = "No existen pagos pendientes para la fecha: " + date;
        }
        return message;
    };

    /**
     * Metodo que permite al usuario diferir en más cuotas un prestamo Aumentando la
     * fecha de vencimiento
     * del prestamo y generando las cuotas correspondientes
     *
     * @param product
     * @param term
     * @return String
     */
    public String deferLoan(Product product, int term) {
        int maxExtensionMonths = 3;
        if (term > maxExtensionMonths) {
            return "No se puede diferir un prestamo a mas de  " + maxExtensionMonths + " meses.";
        }

        LocalDate lastDueDate = product.getInstallments().get(product.getInstallments().size() - 1);
        for (int index = 1; index <= term; index++) {
            product.getInstallments().add(lastDueDate.plusMonths(index));
        }
        product.setTermInMonths(term);
        product.calculateMonthlyPayment();

        return "La fecha de vencimiento de su préstamo ha sido diferida con éxito.";
    }

    /**
     * Metodo que permite al usuario cancelar en su totalidad un producto
     *
     * @param product
     * @return
     */
    public String cancelProduct(Product product) {
        String response = "No se cumplen las condiciones para cancelar el producto";
        if (products.contains(product)) {
            double remainingBalance = product.getRemainingBalance();
            if (spendAmount >= remainingBalance) {
                spendAmount -= remainingBalance;
                products.remove(product);
                response = "Producto cancelado exitosamente";
            }
        }
        return response;
    }

    /**
     * Getters y Setters de la clase
     *
     * @return
     */
    public abstract String getInfo();

    public int getUserType() {
        if (this instanceof RegularCustomer) {
            return 1;
        } else if (this instanceof CasinoCustomer) {
            return 2;
        } else {
            return 3;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getSpendAmount() {
        return spendAmount;
    }

    public void setSpendAmount(int spendAmount) {
        this.spendAmount = spendAmount;
    }

    public int getContractType() {
        return contractType;
    }

    public void setContractType(int contractType) {
        this.contractType = contractType;
    }

    public int getDebts() {
        return debts;
    }

    public void setDebts(int debts) {
        this.debts = debts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Información del usuario";
    }
}
