package com.fintechloans.model.user;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fintechloans.model.product.Product;

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
     * @param contractType Tipo de contrato del usuario
     * @param debts        Deudas del usuario
     * @param score        Puntaje del usuario
     * @param products     Productos del usuario
     * 
     */
    private String name;
    private String email;
    private String password;
    private int age;
    private int income;
    private int spendAmount;
    private String contractType;
    private int debts;
    private int score;
    private ArrayList<Product> products;

    public User(String name, String email, String password, int age, int income, int spendAmount, String contractType,
            int debts) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.income = income;
        this.spendAmount = spendAmount;
        this.contractType = contractType;
        this.debts = debts;
        this.score = 500;
        this.products = new ArrayList<Product>();
    }

    /**
     * Metodo que permite al usuario solicitar un prestamo
     * 
     * @param amount
     * @return Booelan
     */
    public boolean requestLoan(int amount) {
        // Implementar logica de adquisición de prestamos
        return false;
    }

    /**
     * Metodo que permite al usuario pagar cuota de algun prestamo
     * 
     * @param date
     * @param product
     */
    public void payLoanInstallment(LocalDate date, Product product) {
        ArrayList<LocalDate> installments = (ArrayList<LocalDate>) product.getInstallments();
        if (installments.contains(date)) {
            System.out.println("Payment received for installment due on " + date);
            installments.remove(date);
        } else {
            System.out.println("No installment due on " + date);
        }
    }

    /**
     * Metodo que permite al usuario diferir en mas cuotas un prestamo
     * 
     * @param product
     * @param term
     * @return
     */
    public boolean deferLoan(Product product, int term) {
        // Implementar logica de diferir prestamos
        return false;
    }

    /**
     * Metodo que permite al usuario cancelar en su totalidad un producto
     * 
     * @param product
     * @return
     */
    public boolean cancelProduct(Product product) {
        // Implementar logica de cancelar productos
        return false;
    }

    /**
     * Getters y Setters de la clase
     * 
     * @return
     */
    public abstract String getInfo();

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

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
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
