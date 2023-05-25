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
    private String name;
    private String email;
    private String password;
    private int age;
    private int income;
    private int spendAmount;
    private int contractType;
    private int debts;
    private int score;
    private ArrayList<Product> products;

    public User(String name, String email, String password, int age, int income, int spendAmount, int contractType,
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

    private int calculateRisk() {
        int score = 400;
        // Verificaciones de eddad
        int age = this.getAge();
        if (18 <= age && age < 25) {
            score += 0;
        } else if (22 <= age && age < 30) {
            score += 50;
        } else if (30 <= age && age < 60) {
            score += 100;
        } else {
            score += 0;
        }
        // Verificaciones de salario
        int income = this.getIncome();
        if (0 <= income && income < 1500000) {
            score += 0;
        } else if (1500000 <= age && age < 2200000) {
            score += 50;
        } else if (2200000 <= age && age < 4000000) {
            score += 100;
        } else if (4000000 <= age && age < 10000000) {
            score += 200;
        } else {
            score += 250;
        }
        // Verificaiones de contrato
        int contractType = this.getContractType();
        if (contractType == 1 || contractType == 2) {
            score += 50;
        } else {
            score += 0;
        }
        // Verificación de deudas
        int debtsBalance = this.getIncome() - this.getDebts();
        if (debtsBalance < 0) {
            score -= 50;
        } else if (0 <= debtsBalance && debtsBalance < 1000000) {
            score += 0;
        } else if (1000000 <= debtsBalance && debtsBalance < 5000000) {
            score += 50;
        } else {
            score += 100;
        }
        // Redondeando en el tope
        if (score > 1000) {
            int diference = score - 1000;
            score -= diference;
        }
        return score;
    }

    /**
     * Metodo que permite al usuario solicitar un prestamo
     * 
     * @param amount
     * @return Booelan
     */
    public boolean requestLoan(int amount, int term, LocalDate generationDate) {
        // ESTA LINEA ES DE TESTEO
        // Este metodo se basa en el score y en el amount que se recibe como parametro
        // Si el usuario tiene un score mayor a 600 se le permite el prestamo, en caso
        // contrario se le presta menos de lo que pide
        // Crear un producto con sus propiedades y sus datos
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

    // Listar pagos pendientes con fechas
    public String listPendingInstallments(Product product) {
        // Implementar logica para listar pagos pendientes del producto
        String pendingInstallments = "";
        return pendingInstallments;
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
