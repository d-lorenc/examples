package com.dlorenc.examples.bdd.jbehave.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.dlorenc.examples.bdd.jbehave.BankAccount;

public class AccountSteps {

    private BankAccount account;
    private IllegalArgumentException failure;

    @Given("a bank account with initial balance $initialBalance")
    public void createAccountWithBalance(BigDecimal initialBalance) {
        account = new BankAccount(initialBalance);
    }

    @When("I withdraw $amount")
    @Alias("I try to withdraw $amount")
    public void withdraw(BigDecimal amount) {
        try {
            account.withdraw(amount);
        } catch (IllegalArgumentException e) {
            failure = e;
        }
    }

    @When(value="I withdraw $amounts", priority=1)
    public void withdraw(List<BigDecimal> amounts) {
        for (BigDecimal amount : amounts) {
            account.withdraw(amount);
        }
    }
    
    @When("I deposit $amount")
    public void deposit(BigDecimal amount) {
        account.deposit(amount);
    }

    @Then("remaining balance is $expectedBalance")
    @Alias("new balance is $expectedBalance")
    public void assertBalance(BigDecimal expectedBalance) {
        assertEquals(expectedBalance, account.getBalance());
    }
    
    @Then("transaction is rejected due to \"$expectedReason\"")
    public void transactionRejected(String expectedReason) {
        assertNotNull(failure);
        assertEquals(expectedReason, failure.getMessage());
    }
    
}
