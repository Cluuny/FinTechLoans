package com.fintechloans.model.product;

import java.time.LocalDate;

public class VirtualCard extends Product {
    public VirtualCard(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        super(loanAmount, interestRate, termInMonths, dueDate);
    }

/*
    public VirtualCard(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        super(loanAmount, interestRate, termInMonths, dueDate);
    }

 */
}