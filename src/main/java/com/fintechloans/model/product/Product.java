package com.fintechloans.model.product;

public abstract class Product {

    public abstract Product payInstallment(int term, int amount);

    public abstract String cancelProduct();

    public abstract Product differ(int amount);
}