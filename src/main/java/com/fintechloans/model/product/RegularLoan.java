package com.fintechloans.model.product;

public class RegularLoan extends Product {
    private int id;
    private int amount;
    private int term;
    private int interestRate;
    private Boolean itsOverDated;

    public RegularLoan(int id, int amount, int term, int interestRate, int overDue) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
        this.itsOverDated = false;
    }

    @Override
    public Product payInstallment(int term, int amount) {
        // Implementation for paying installment
        // Update the loan state and return the updated product
        return this;
    }

    @Override
    public String cancelProduct() {
        // Implementation for canceling the regular loan
        // Return a cancellation message
        return "Regular loan canceled.";
    }

    @Override
    public Product differ(int amount) {
        // Implementation for differing the regular loan
        // Update the loan state and return the updated product
        return this;
    }
}
