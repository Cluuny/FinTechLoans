package com.fintechloans.model.product;

public class VirtualCard extends Product {

    private int digits;
    private String expirationDate;
    private int CCV;
    private String provider;
    private int balance;

    public VirtualCard(int digits, String expirationDate, int CCV, String provider, int balance) {
        this.digits = digits;
        this.expirationDate = expirationDate;
        this.CCV = CCV;
        this.provider = provider;
        this.balance = balance;
    }

    @Override
    public Product payInstallment(int term, int amount) {
        // Implementation for paying installment for the virtual card
        // Update the virtual card state and return the updated product
        return this;
    }

    @Override
    public String cancelProduct() {
        // Implementation for canceling the virtual card
        // Return a cancellation message
        return "Virtual card canceled.";
    }

    @Override
    public Product differ(int amount) {
        // Implementation for differing the virtual card
        // Update the virtual card state and return the updated product
        return this;
    }
}