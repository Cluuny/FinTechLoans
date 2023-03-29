package model.user;

import model.Services.*;

public class RegularCustomer extends User {

    public RegularCustomer(String name, String email, int age, int income, String contractType, int debts, int score,
            InternalHistory internalHistory) {
        super(name, email, age, income, contractType, debts, score, internalHistory);
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }

}
