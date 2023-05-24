package com.fintechloans.model.product;

import java.time.LocalDate;

public class RegularLoan extends Product {

    public RegularLoan(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        super(loanAmount, interestRate, termInMonths, dueDate);
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
