package com.fintechloans.model.user;

import com.fintechloans.model.services.*;

public class RegularCustomer extends User {

    public RegularCustomer(String name, String email, String password, int age, int income, String contractType,
            int debts) {
        super(name, email, password, age, income, contractType, debts);
    }

    @Override
    public String getInfo() {
        return this.getEmail() + "\n" + this.getName() + "\n";
    }

}
