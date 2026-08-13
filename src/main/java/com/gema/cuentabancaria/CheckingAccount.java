package com.gema.cuentabancaria;

public class CheckingAccount extends Account {

    protected float overdraft;

    public CheckingAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.overdraft = 0;
    }

    @Override
    public void withdraw(float amount) {
        this.balance -= amount;
        this.numberOfWithdrawals++;
        this.overdraft = this.balance < 0 ? -this.balance : 0;
    }

    @Override
    public void deposit(float amount) {
        super.deposit(amount);
        this.overdraft = this.balance < 0 ? -this.balance : 0;
    }

    @Override
    public void monthlyStatement() {
        super.monthlyStatement();
    }

    @Override
    public String print() {
        int numberOfTransactions = this.numberOfDeposits + this.numberOfWithdrawals;
        return "Balance: " + this.balance +
               ", Monthly fee: " + this.monthlyFee +
               ", Number of transactions: " + numberOfTransactions +
               ", Overdraft: " + this.overdraft;
    }
}