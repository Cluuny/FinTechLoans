package com.fintechloans.model.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Clase que representa un producto financiero, es la clase padre de todos los
 * productos financieros
 * 
 * @author Vicente Matallana, Gabriel Cely, Sebastian Cañon
 * @version 1.0.1
 */
public class Product {

    /*
     * Atributos de la clase Product
     * 
     * @param id Identificador del producto
     * @param loanAmount Monto del préstamo
     * @param interestRate Tasa de interés
     * @param termInMonths Plazo en meses
     * @param remainingBalance Saldo restante
     * @param monthlyPayment Pago mensual
     * @param startDate Fecha de inicio
     * @param dueDate Fecha de vencimiento
     * @param installments ArrayList que contiene las fechas de las cuotas
     * @param paidOff Booleano que indica si el producto está pagado
     * @param isOverDue Booleano que indica si el producto está en mora
     * 
     */
    protected int id;
    protected double loanAmount;
    protected double interestRate;
    protected int termInMonths;
    protected double remainingBalance;
    protected double monthlyPayment;
    protected LocalDate startDate;
    protected LocalDate dueDate;

    protected List<LocalDate> installments;
    protected boolean paidOff;
    protected boolean isOverDue;

    public Product(double loanAmount, int termInMonths, LocalDate startDate) {
        this.id = (int) Math.random() * 1000;
        this.loanAmount = loanAmount;
        this.interestRate = 0.05;
        this.termInMonths = termInMonths;
        this.startDate = startDate;
        this.dueDate = startDate.plusMonths(1);
        this.installments = new ArrayList<>();
        this.paidOff = false;
        this.isOverDue = false;
        this.remainingBalance = loanAmount;
        this.id = generateRandomId();

    }

    /**
     * Método que verifica si la cuota está en mora
     * 
     * @param currentDate
     * @return String
     */
    public String checkOverdueStatus(LocalDate currentDate) {
        String message = null;
        if (!installments.isEmpty() && currentDate.isAfter(dueDate)) {
            message = ("La cuota está en mora. Por favor pague pronto para evitar estar reportado en centrales de riesgo");
            isOverDue = true;

        }
        return message;
    }

    /**
     * Método que genera un id aleatorio
     * 
     * @return int
     */
    private int generateRandomId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    // Metodo para generar fechas de installments
    public void generateInstallments() {
        this.installments.add(this.dueDate);
        for (int i = 1; i < termInMonths; i++) {
            this.installments.add(this.dueDate.plusMonths(i));
        }
    }

    // Metodo para calcular el pago mensual
    public double calculateMonthlyPayment() {
        double monthlyPayment = this.loanAmount / this.termInMonths;
        return monthlyPayment * interestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(int termInMonths) {
        this.termInMonths = termInMonths;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setPaidOff(boolean paidOff) {
        this.paidOff = paidOff;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public boolean isOverDue() {
        return isOverDue;
    }

    public void setOverDue(boolean isOverDue) {
        this.isOverDue = isOverDue;
    }

    public List<LocalDate> getInstallments() {
        return installments;
    }

    public boolean isPaidOffLoan() {
        return paidOff;
    }

    @Override
    public String toString() {
        return "Id del prestamo: " + this.getId() + "\n" + "Monto prestado: " + this.getLoanAmount() + "\n"
                + "Numero de cuotas: "
                + this.getTermInMonths() + "\n"
                + "Fecha de inicialización: " + this.getStartDate();
    }
}