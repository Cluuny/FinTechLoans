package com.fintechloans.model.product;

import java.time.LocalDate;

public class VirtualCard extends Product {
    private int cardNumber;
    private int cvv;
    private String cardHolderName;
    private LocalDate expirationDate;

    public VirtualCard(double loanAmount, double interestRate, int termInMonths, LocalDate dueDate) {
        super(loanAmount, interestRate, termInMonths, dueDate);
    }

    /**
     * Getters y Setters
     */
    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

}