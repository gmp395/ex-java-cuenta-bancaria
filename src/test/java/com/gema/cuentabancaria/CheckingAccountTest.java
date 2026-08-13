package com.gema.cuentabancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    private CheckingAccount account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount(1000, 6);
    }

    @Test
    void shouldStartWithZeroOverdraft() {
        assertEquals(0, account.overdraft, 0.01f);
    }

    @Test
    void shouldAllowWithdrawWithinBalance() {
        account.withdraw(400);
        assertEquals(600, account.balance, 0.01f);
        assertEquals(0, account.overdraft, 0.01f);
    }

    @Test
    void shouldGoNegativeAndCreateOverdraftWhenWithdrawExceedsBalance() {
        account.withdraw(1500);
        assertEquals(-500, account.balance, 0.01f);
        assertEquals(500, account.overdraft, 0.01f);
    }

    @Test
    void depositShouldReduceOverdraft() {
        account.withdraw(1500);
        account.deposit(300);
        assertEquals(-200, account.balance, 0.01f);
        assertEquals(200, account.overdraft, 0.01f);
    }

    @Test
    void depositShouldClearOverdraftWhenFullyCovered() {
        account.withdraw(1500);
        account.deposit(600);
        assertEquals(100, account.balance, 0.01f);
        assertEquals(0, account.overdraft, 0.01f);
    }

    @Test
    void printShouldIncludeOverdraft() {
        account.withdraw(1500);
        String result = account.print();
        assertTrue(result.contains("Overdraft: 500.0"));
    }

    @Test
    void monthlyStatementShouldApplyInterestLikeParent() {
        account.monthlyStatement();
        assertEquals(1005.0, account.balance, 0.01f);
    }
}