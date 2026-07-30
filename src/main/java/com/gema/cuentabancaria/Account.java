package com.gema.cuentabancaria;

public class Account {
    protected float balance;
    protected int numberOfDeposits;
    protected int numberOfWithdrawals;
    protected float annualInterestRate;
    protected float monthlyFee;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.numberOfDeposits = 0;
        this.numberOfWithdrawals = 0;
        this.monthlyFee = 0;
    }

    public void deposit(float amount) {
        this.balance += amount;
        this.numberOfDeposits++;
    }

    public void withdraw(float amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.numberOfWithdrawals++;
        }
    }

    public void calculateMonthlyInterest() {
        float monthlyRate = this.annualInterestRate / 12 / 100;
        float interest = this.balance * monthlyRate;
        this.balance += interest;
    }

    public void monthlyStatement() {
        this.balance -= this.monthlyFee;
        calculateMonthlyInterest();
    }

    public String print() {
        return "Balance: " + this.balance +
               ", Number of deposits: " + this.numberOfDeposits +
               ", Number of withdrawals: " + this.numberOfWithdrawals +
               ", Annual interest rate: " + this.annualInterestRate +
               ", Monthly fee: " + this.monthlyFee;
    }
}