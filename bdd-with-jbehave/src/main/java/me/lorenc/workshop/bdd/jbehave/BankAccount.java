package me.lorenc.workshop.bdd.jbehave;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal balance;

    public static BankAccount createEmpty() {
        return new BankAccount(BigDecimal.ZERO);
    }
    
    public BankAccount(BigDecimal balance) {
        this.balance = balance;
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) == 1) {
            throw new IllegalArgumentException("insufficient funds");
        }
        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
