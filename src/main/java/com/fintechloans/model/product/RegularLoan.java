package com.fintechloans.model.product;

import java.time.LocalDate;

public class RegularLoan extends Product {
    private int id;
    private int amount;
    private int term;
    private int interestRate;
    private Boolean itsOverDated;

    public RegularLoan(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        super(loanAmount, interestRate, termInMonths, dueDate);
    }

    @Override
    public void payInstallment(LocalDate date) {
        super.payInstallment(date);
        checkOverdueStatus(LocalDate.now());
    }

    @Override
    public void checkOverdueStatus(LocalDate currentDate) {
        super.checkOverdueStatus(currentDate);
        if (isOverdue() && !isPaidOff()) {
            System.out.println("You are overdue. Please pay quickly to avoid being reported to credit bureaus.");
        }
    }

    private boolean isOverdue() {
        return !getInstallments().isEmpty() && LocalDate.now().isAfter(getDueDate());
    }

    private boolean isPaidOff() {
        return isPaidOffLoan();
    }
}
