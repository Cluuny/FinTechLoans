package model.Product;

public abstract class Product {

    public abstract Product payInstallment(int term, int amount);

    public abstract String cancelProdcut();

    public abstract Product differ(int amount);
}
