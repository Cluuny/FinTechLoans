package model.user;

import model.Services.*;

public abstract class User {
    private String name;
    private String email;
    private int age;
    private int income;
    private String contractType;
    private int debts;
    private int score;
    private InternalHistory InternalHistory;

    public User(String name, String email, int age, int income, String contractType, int debts, int score,
            InternalHistory internalHistory) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.income = income;
        this.contractType = contractType;
        this.debts = debts;
        this.score = score;
        InternalHistory = internalHistory;
    }

    //Pendiente: añadir metodos de pago para el usuario

    public abstract String getInfo();
}
