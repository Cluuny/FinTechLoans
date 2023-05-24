package com.fintechloans.model.user;

import java.util.ArrayList;

public class CasinoCustomer extends User {
    private ArrayList<Integer> gameStats;

    public CasinoCustomer(String name, String email, String password, int age, int income, int spendAmount,
            int contractType,
            int debts,
            ArrayList<Integer> gameStast) {
        super(name, email, password, age, income, spendAmount, contractType, debts);
        this.gameStats = gameStast;
    }

    public ArrayList<Integer> getGameStats() {
        return gameStats;
    }

    public void setGameStats(ArrayList<Integer> gameStats) {
        this.gameStats = gameStats;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Unimplemented method 'getInfo'");
    }

}
