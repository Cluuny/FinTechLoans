package com.fintechloans.model.services;

import java.util.ArrayList;

import com.fintechloans.model.product.*;

public class InternalHistory {
    private ArrayList<Product> internalHistory = new ArrayList<Product>();

    public String appendProduct(Product product) {
        if (internalHistory.contains(product)) {
            return "El producto ya existe.";
        }
        internalHistory.add(product);
        return "Producto agregado correactamente.";
    }
}
