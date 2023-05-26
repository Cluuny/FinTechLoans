package com.fintechloans.model.product;

import java.time.LocalDate;

public class CasinoLoan extends Product {

    public CasinoLoan(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        super(loanAmount, termInMonths, dueDate);
    }

}
