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

    public void addInstallment(LocalDate date) {
        installments.add(date);
    }

    public void payInstallment(LocalDate date) {
        if (installments.contains(date)) {
            System.out.println("Payment received for installment due on " + date);
            installments.remove(date);
        } else {
            System.out.println("No installment due on " + date);
        }
    }

    public void payOffLoan() {
        paidOff = true;
        System.out.println("Loan has been paid off.");
    }

    public void deferInstallments(int monthsToDefer) {
        LocalDate newDueDate = dueDate.plusMonths(monthsToDefer);
        System.out.println("Installments deferred. New due date is " + newDueDate);
        dueDate = newDueDate;
    }

    public void checkOverdueStatus(LocalDate currentDate) {
        if (!installments.isEmpty() && currentDate.isAfter(dueDate)) {
            System.out.println("Installment is overdue. Please pay quickly to avoid being reported to credit bureaus.");
            isOverDue = true;
        }
    }

    protected List<LocalDate> getInstallments() {
        return installments;
    }

    protected boolean isPaidOffLoan() {
        return paidOff;
    }

}