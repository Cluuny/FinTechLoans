package com.fintechloans.model.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Product {
    private int id;
    private double loanAmount;
    private double interestRate;
    private int termInMonths;
    private double remainingBalance;
    private double monthlyPayment;
    // Pendiente revisión de fechas
    private LocalDate startDate;
    private LocalDate dueDate;

    private List<LocalDate> installments;
    private boolean paidOff;
    private boolean isOverDue;

    public Product(double loanAmount, int termInMonths, LocalDate startDate) {
        this.id = (int) Math.random() * 1000;
        this.loanAmount = loanAmount;
        this.interestRate = 33.333333;
        this.termInMonths = termInMonths;
        this.monthlyPayment = this.calculateMonthlyPayment();
        this.startDate = startDate;
        this.dueDate = startDate.plusMonths(1);
        // Implementar logica de creación de fechas
        this.installments = new ArrayList<>();
        this.generateInstallments();
        this.paidOff = false;
        this.isOverDue = false;
        this.remainingBalance = loanAmount;
        this.id = generateRandomId();
    }

    // Abstraer metodos
    public String checkOverdueStatus(LocalDate currentDate) {
        String message = null;
        if (!installments.isEmpty() && currentDate.isAfter(dueDate)) {
            message = ("La cuota está en mora. Por favor pague pronto para evitar estar reportado en centrales de riesgo");
            isOverDue = true;

        }
        return message;
    }

    //generador de ids
    private int generateRandomId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    // Metodo para generar fechas de installments
    public void generateInstallments() {
        this.installments.add(this.dueDate);
        for (int i = 1; i <= termInMonths; i++) {
            this.installments.add(this.dueDate.plusMonths(i));
        }
    }

    // Metodo para calcular el pago mensual
    public double calculateMonthlyPayment() {
        double monthlyInterestRate = interestRate / 12.0;
        double numerator = loanAmount * monthlyInterestRate;
        //loan amortization formula
        //loan repayment formula
        double denominator = 1 - Math.pow(1 + monthlyInterestRate, -termInMonths);
        return numerator / denominator;
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
        return "Monto prestado: " + this.getLoanAmount() + "\n" + "Numero de cuotas: " + this.getTermInMonths() + "\n"
                + "Fecha de inicialización" + this.getStartDate();
    }
}