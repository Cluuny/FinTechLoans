package model.product;

public class RegularLoan extends Product {
    private int id;
    private int amount;
    private int term;
    private int interestRate;
    private int overDue;

    public RegularLoan(int id, int amount, int term, int interestRate, int overDue) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
        this.overDue = overDue;
    }

    @Override
    public Product payInstallment(int term, int amount) {
        throw new UnsupportedOperationException("Unimplemented method 'payInstallment'");
    }

    @Override
    public String cancelProdcut() {
        throw new UnsupportedOperationException("Unimplemented method 'cancelProdcut'");
    }

    @Override
    public Product differ(int amount) {
        throw new UnsupportedOperationException("Unimplemented method 'differ'");
    }

}
