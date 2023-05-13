package com.fintechloans.model.user;

import com.fintechloans.model.services.*;

public class RegularCustomer extends User {

    public RegularCustomer(String name, String email, int age, int income, String contractType, int debts) {
        super(name, email, age, income, contractType, debts);
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }

}
