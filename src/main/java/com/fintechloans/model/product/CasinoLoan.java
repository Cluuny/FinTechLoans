package com.fintechloans.model.product;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Clase que representa un producto de tipo prestamo de casino
 * 
 * @author Vicente Matallana, Gabriel Cely, Sebastian Cañon
 * @version 1.0.1
 */
public class CasinoLoan extends Product {
    private static final int PAYMENT_INTERVAL_HOURS = 1;

    public CasinoLoan(double loanAmount, int termInHours, LocalDate startDate) {
        super(loanAmount, termInHours, LocalDate.from(startDate));
        this.setDueDate(startDate);
    }

    /** 
     * Método para generar las cuotas del prestamo
     * 
     * @return void
     */
    @Override
    public void generateInstallments() {
        LocalDateTime currentDateTime = startDate.atStartOfDay();
        this.installments.add(LocalDate.from(currentDateTime));
        for (int i = 1; i <= termInMonths; i++) {
            currentDateTime = currentDateTime.plusHours(i * PAYMENT_INTERVAL_HOURS);
            this.installments.add(LocalDate.from(currentDateTime));
        }
    }

    
    /** 
     * Método para verificar si el pago está atrasado o no
     * 
     * @param currentDate
     * @return String
     */
    @Override
    public String checkOverdueStatus(LocalDate currentDate) {
        String message = null;
        LocalDateTime currentDateTime = currentDate.atStartOfDay();
        if (!installments.isEmpty() && currentDateTime.isAfter(dueDate.atStartOfDay())) {
            long hoursLate = Duration.between(dueDate.atStartOfDay(), currentDateTime).toHours();
            message = "El pago está tarde en  " + hoursLate
                    + " horas. Pague lo antes posible para evitar ser reportado a centrales de riesgo";
            isOverDue = true;
        }
        return message;
    }

    /** 
     * Método para calcular el pago mensual del prestamo de casino con intereses
     * 
     * @return double
     */
    @Override
    public double calculateMonthlyPayment() {
        double hourlyInterestRate = interestRate / (30 * 24); // Assuming 30 days in a month and 24 hours in a day
        double numerator = loanAmount * hourlyInterestRate;
        double denominator = 1 - Math.pow(1 + hourlyInterestRate, -termInMonths);
        return numerator / denominator;
    }

    @Override
    public String toString() {
        return "Loan amount: " + this.getLoanAmount() + "\n"
                + "Term in hours: " + this.getTermInMonths() + "\n"
                + "Start date: " + this.getStartDate();
    }
}
