package com.gema.cuentabancaria;

public class SavingsAccount extends Account {

    protected boolean active;

    public SavingsAccount(float balance, float annualInterestRate) {
        super(balance, annualInterestRate);
        this.active = balance >= 10000;
    }

    @Override
    public void deposit(float amount) {
        if (this.active) {
            super.deposit(amount);
        }
    }

    @Override
    public void withdraw(float amount) {
        if (this.active) {
            super.withdraw(amount);
        }
    }

    @Override
    public void monthlyStatement() {
        if (this.numberOfWithdrawals > 4) {
            int extraWithdrawals = this.numberOfWithdrawals - 4;
            this.monthlyFee = extraWithdrawals * 1000;
        } else {
            this.monthlyFee = 0;
        }
        super.monthlyStatement();
        this.active = this.balance >= 10000;
    }

    @Override
    public String print() {
        int numberOfTransactions = this.numberOfDeposits + this.numberOfWithdrawals;
        return "Balance: " + this.balance +
               ", Monthly fee: " + this.monthlyFee +
               ", Number of transactions: " + numberOfTransactions;
    }
}
