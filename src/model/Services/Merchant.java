package model.Services;

public class Merchant {

    private String merchantType;
    private String merchantName;
    private int merchantItems;

    public Merchant(String merchantType, String merchantName, int merchantItems) {
        this.merchantType = merchantType;
        this.merchantName = merchantName;
        this.merchantItems = merchantItems;
    }

}
