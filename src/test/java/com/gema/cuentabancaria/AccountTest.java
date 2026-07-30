package com.gema.cuentabancaria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void constructorInitializesAttributesCorrectly() {
        Account account = new Account(50000, 2.5f);

        assertEquals(50000, account.balance);
        assertEquals(2.5f, account.annualInterestRate);
        assertEquals(0, account.numberOfDeposits);
        assertEquals(0, account.numberOfWithdrawals);
        assertEquals(0, account.monthlyFee);
    }

    @Test
    void depositIncreasesBalanceAndCounter() {
        Account account = new Account(50000, 2.5f);

        account.deposit(10000);

        assertEquals(60000, account.balance);
        assertEquals(1, account.numberOfDeposits);
    }

@Test
void withdrawDecreasesBalanceWhenAmountIsValid() {
    Account account = new Account(50000, 2.5f);

    account.withdraw(20000);

    assertEquals(30000, account.balance);
    assertEquals(1, account.numberOfWithdrawals);
}

@Test
void withdrawDoesNothingWhenAmountExceedsBalance() {
    Account account = new Account(50000, 2.5f);

    account.withdraw(60000);

    assertEquals(50000, account.balance);
    assertEquals(0, account.numberOfWithdrawals);
}

 @Test
    void calculateMonthlyInterestAddsCorrectAmountToBalance() {
        Account account = new Account(50000, 12f);

        account.calculateMonthlyInterest();

        assertEquals(50500, account.balance);
    }

@Test
void monthlyStatementSubtractsFeeAndAddsInterest() {
    Account account = new Account(50000, 12f);
    account.monthlyFee = 1000;

    account.monthlyStatement();

    assertEquals(49490, account.balance);
}

@Test
void printReturnsAllAttributeValues() {
    Account account = new Account(50000, 2.5f);

    String result = account.print();

    assertTrue(result.contains("50000"));
    assertTrue(result.contains("2.5"));
    assertTrue(result.contains("0"));
}
}