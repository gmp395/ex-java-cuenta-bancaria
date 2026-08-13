package com.gema.cuentabancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    private SavingsAccount account;

    @BeforeEach
    void setUp() {
        account = new SavingsAccount(15000, 12);
    }

    @Test
    void shouldBeActiveWhenInitialBalanceIsAtLeast10000() {
        assertTrue(account.active);
    }

    @Test
    void shouldBeInactiveWhenInitialBalanceIsBelow10000() {
        SavingsAccount inactiveAccount = new SavingsAccount(5000, 12);
        assertFalse(inactiveAccount.active);
    }

    @Test
    void shouldDepositWhenActive() {
        account.deposit(1000);
        assertEquals(16000, account.balance, 0.01f);
        assertEquals(1, account.numberOfDeposits);
    }

    @Test
    void shouldNotDepositWhenInactive() {
        SavingsAccount inactiveAccount = new SavingsAccount(5000, 12);
        inactiveAccount.deposit(1000);
        assertEquals(5000, inactiveAccount.balance, 0.01f);
        assertEquals(0, inactiveAccount.numberOfDeposits);
    }

    @Test
    void shouldWithdrawWhenActive() {
        account.withdraw(1000);
        assertEquals(14000, account.balance, 0.01f);
        assertEquals(1, account.numberOfWithdrawals);
    }

    @Test
    void shouldNotWithdrawWhenInactive() {
        SavingsAccount inactiveAccount = new SavingsAccount(5000, 12);
        inactiveAccount.withdraw(500);
        assertEquals(5000, inactiveAccount.balance, 0.01f);
        assertEquals(0, inactiveAccount.numberOfWithdrawals);
    }

    @Test
    void shouldChargeFeeForWithdrawalsBeyondFourth() {
        for (int i = 0; i < 6; i++) {
            account.withdraw(100);
        }
        account.monthlyStatement();
        assertEquals(2000, account.monthlyFee, 0.01f);
    }

    @Test
    void shouldNotChargeFeeWithFourOrFewerWithdrawals() {
        for (int i = 0; i < 4; i++) {
            account.withdraw(100);
        }
        account.monthlyStatement();
        assertEquals(0, account.monthlyFee, 0.01f);
    }

    @Test
    void shouldBecomeInactiveAfterStatementIfBalanceDropsBelowThreshold() {
        SavingsAccount lowAccount = new SavingsAccount(10500, 0);
        lowAccount.withdraw(600);
        lowAccount.monthlyStatement();
        assertFalse(lowAccount.active);
    }

    @Test
    void printShouldIncludeNumberOfTransactions() {
        account.deposit(500);
        account.withdraw(200);
        String result = account.print();
        assertTrue(result.contains("Number of transactions: 2"));
    }
}
