package com.fintechloans.model.user;

import java.util.ArrayList;
import com.fintechloans.model.services.*;

public class CasinoCustomer extends User {
    private ArrayList<Integer> gameStats;

    public CasinoCustomer(String name, String email, int age, int income, String contractType, int debts,
            ArrayList<Integer> gameStast) {
        super(name, email, age, income, contractType, debts);
        this.gameStats = gameStast;
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }

}
