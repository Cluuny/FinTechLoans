package com.fintechloans.model.user;

public class RegularCustomer extends User {

    public RegularCustomer(String name, String email, String password, int age, int income, int spendAmount,
            int contractType,
            int debts) {
        super(name, email, password, age, income, spendAmount, contractType, debts);
    }

    @Override
    public String getInfo() {
        return this.getEmail() + "\n" + this.getName() + "\n";
    }

}
