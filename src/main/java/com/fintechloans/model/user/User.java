package com.fintechloans.model.user;

import com.fintechloans.model.services.*;

public abstract class User {
    private String name;
    private String email;
    private int age;
    private int income;
    private String contractType;
    private int debts;
    private int score;
    private InternalHistory InternalHistory;

    public User(String name, String email, int age, int income, String contractType, int debts) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.income = income;
        this.contractType = contractType;
        this.debts = debts;
        this.score = score = 0;
        InternalHistory = new InternalHistory();
    }

    // Pendiente: añadir metodos de pago para el usuario

    public abstract String getInfo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public int getDebts() {
        return debts;
    }

    public void setDebts(int debts) {
        this.debts = debts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public InternalHistory getInternalHistory() {
        return InternalHistory;
    }

    public void setInternalHistory(InternalHistory internalHistory) {
        InternalHistory = internalHistory;
    }

    @Override
    public String toString() {
        return "A";
    }
}
