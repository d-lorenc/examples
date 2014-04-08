Story #1 - Withdraw from account

Narrative:
In order to effectively manage my funds
As a owner of a bank account
I want to be able to withdraw my money


Scenario: Should be able to withdraw all the money

Given a bank account with initial balance 10
When I withdraw 10
Then remaining balance is 0


Scenario: Should be able to withdraw some money

Given a bank account with initial balance <initial>
When I withdraw <amount>
Then remaining balance is <balance>

Examples:
|initial|amount|balance|
|    10|      9|      1|
|    10|      1|      9|
|   100|     20|     80|


Scenario: Should not be able to withdraw more than balance

Given a bank account with initial balance 10
When I try to withdraw 11
Then transaction is rejected due to "insufficient funds"


Scenario: Should be able to withdraw multiple times

Given a bank account with initial balance 100
When I withdraw 10, 20, 30
Then new balance is 40