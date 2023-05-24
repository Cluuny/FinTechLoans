package com.fintechloans.model.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    private double loanAmount;
    private double interestRate;
    private int termInMonths;
    private LocalDate dueDate;
    private List<LocalDate> installments;
    private boolean paidOff;
    private boolean isOverDue;

    public Product(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.termInMonths = termInMonths;
        this.dueDate = dueDate;
        this.installments = new ArrayList<>();
        this.paidOff = false;
        this.isOverDue = false;
    }

    public void checkOverdueStatus(LocalDate currentDate) {
        if (!installments.isEmpty() && currentDate.isAfter(dueDate)) {
            System.out.println("Installment is overdue. Please pay quickly to avoid being reported to credit bureaus.");
            isOverDue = true;
        }
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

    public List<LocalDate> getInstallments() {
        return installments;
    }

    protected boolean isPaidOffLoan() {
        return paidOff;
    }

}