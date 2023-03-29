package model.product;

public class VirtualCard extends Product {

    private int digits;
    private String expirationDate;
    private int CCV;
    private String provider;
    private int balance;

    public VirtualCard(int digits, String expirationDate, int cCV, String provider, int balance) {
        this.digits = digits;
        this.expirationDate = expirationDate;
        CCV = cCV;
        this.provider = provider;
        this.balance = balance;
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
