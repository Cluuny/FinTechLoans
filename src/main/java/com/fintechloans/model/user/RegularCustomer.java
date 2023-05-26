package com.fintechloans.model.user;

import java.time.LocalDate;

import com.fintechloans.model.product.*;

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

    @Override
    protected int calculateRisk() {
        int score = 400;
        // Verificaciones de eddad
        int age = this.getAge();
        if (18 <= age && age < 25) {
            score += 0;
        } else if (22 <= age && age < 30) {
            score += 50;
        } else if (30 <= age && age < 60) {
            score += 100;
        } else {
            score += 0;
        }
        // Verificaciones de salario
        int income = this.getIncome();
        if (0 <= income && income < 1500000) {
            score += 0;
        } else if (1500000 <= age && age < 2200000) {
            score += 50;
        } else if (2200000 <= age && age < 4000000) {
            score += 100;
        } else if (4000000 <= age && age < 10000000) {
            score += 200;
        } else {
            score += 250;
        }
        // Verificaiones de contrato
        int contractType = this.getContractType();
        if (contractType == 1 || contractType == 2) {
            score += 50;
        } else {
            score += 0;
        }
        // Verificación de deudas
        int debtsBalance = this.getIncome() - this.getDebts();
        if (debtsBalance < 0) {
            score -= 50;
        } else if (0 <= debtsBalance && debtsBalance < 1000000) {
            score += 0;
        } else if (1000000 <= debtsBalance && debtsBalance < 5000000) {
            score += 50;
        } else {
            score += 100;
        }
        // Redondeando en el tope
        if (score > 1000) {
            int diference = score - 1000;
            score -= diference;
        }
        return score;
    }

    @Override
    public String requestLoan(double amount, int term, LocalDate generationDate) {
        if (score >= 600) {
            // Allow the loan request
            Product loan = new RegularLoan(amount, term, generationDate);
            products.add((RegularLoan) loan);
            spendAmount += amount;

            double monthlyPayment = loan.calculateMonthlyPayment();
            loan.setMonthlyPayment(monthlyPayment);

            loan.generateInstallments();

            loan.setRemainingBalance(amount);

            return "Su solicitud ha sido aprobada con un monto de: " + amount + "\n" + "El id de su prestamo es: "
                    + loan.getId();
        } else {
            // Grant a loan amount less than requested
            double grantedAmount = (double) amount * 0.75;
            if (grantedAmount > 0) {
                Product loan = new RegularLoan(grantedAmount, term, generationDate);
                products.add((RegularLoan) loan);
                spendAmount += grantedAmount;

                double monthlyPayment = loan.calculateMonthlyPayment();
                loan.setMonthlyPayment(monthlyPayment);

                loan.generateInstallments();

                loan.setRemainingBalance(grantedAmount);

                return "Su solicitud ha sido aprobada con un monto de: " + grantedAmount + "\n"
                        + "Esto debido a que su puntaje es menor a 600" + "\n" + "El id de su prestamo es: "
                        + loan.getId();
            } else {
                return "Su solcitud no ha sido aprobada por nuestros asesores"; // User is not eligible for a loan
            }
        }
    }
}
