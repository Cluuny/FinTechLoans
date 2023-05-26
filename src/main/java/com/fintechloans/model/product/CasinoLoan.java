package com.fintechloans.model.product;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CasinoLoan extends Product {
    private static final int PAYMENT_INTERVAL_HOURS = 1;

    public CasinoLoan(double loanAmount, int termInHours, LocalDate startDate) {
        super(loanAmount, termInHours, LocalDate.from(startDate));
    }


    @Override
    public void generateInstallments() {
        LocalDateTime currentDateTime = startDate.atStartOfDay();
        this.installments.add(LocalDate.from(currentDateTime));
        for (int i = 1; i <= termInMonths; i++) {
            currentDateTime = currentDateTime.plusHours(i * PAYMENT_INTERVAL_HOURS);
            this.installments.add(LocalDate.from(currentDateTime));
        }
    }


    @Override
    public String checkOverdueStatus(LocalDate currentDate) {
        String message = null;
        LocalDateTime currentDateTime = currentDate.atStartOfDay();
        if (!installments.isEmpty() && currentDateTime.isAfter(dueDate.atStartOfDay())) {
            long hoursLate = Duration.between(dueDate.atStartOfDay(), currentDateTime).toHours();
            message = "El pago está tarde en  " + hoursLate + " horas. Pague lo antes posible para evitar ser reportado a centrales de riesgo";
            isOverDue = true;
        }
        return message;
    }

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
