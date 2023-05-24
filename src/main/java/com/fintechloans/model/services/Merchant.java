package com.fintechloans.model.services;

public class Merchant {

    private Boolean isAllied;
    private String merchantName;

    public Merchant(Boolean isAllied, String merchantName, int merchantItems) {
        this.isAllied = isAllied;
        this.merchantName = merchantName;
    }

    /**
     * Getters y Setters
     */
    public Boolean getIsAllied() {
        return isAllied;
    }

    public void setIsAllied(Boolean isAllied) {
        this.isAllied = isAllied;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

}
