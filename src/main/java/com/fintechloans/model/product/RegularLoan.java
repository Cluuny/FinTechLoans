package com.fintechloans.model.product;

import java.time.LocalDate;

public class RegularLoan extends Product {

    public RegularLoan(double loanAmount, int termInMonths, LocalDate dueDate) {
        super(loanAmount, termInMonths, dueDate);
    }

    @Override
    public String checkOverdueStatus(LocalDate currentDate) {
        super.checkOverdueStatus(currentDate);
        if (isOverdue() && !isPaidOff()) {
            System.out.println("You are overdue. Please pay quickly to avoid being reported to credit bureaus.");
        }
        return null;
    }

    private boolean isOverdue() {
        return !getInstallments().isEmpty() && LocalDate.now().isAfter(getDueDate());
    }

    private boolean isPaidOff() {
        return isPaidOffLoan();
    }
}
