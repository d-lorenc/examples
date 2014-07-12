User Story #3 - Account Rest Service

Narrative:
In order to access my account remotely
As a geek
I want to have my bank account available as a REST service

Hmm... Is the JSON representation of money worth the same as real money? Not sure. Probably haven't thought that one trough, have I?
					 
Scenario: Should be able to deposit money

Given an account REST service
When I POST to /account/deposit amount <amount>
Then GET request to /account returns balance <amount>

Examples:
|amount|
|     1|
|    10|
|   100|


Scenario: Should be able to withdraw money after deposit

Given an account REST service
When I POST to /account/deposit amount 100
And I POST to /account/withdraw amount 20
Then GET request to /account returns balance 80 


Scenario: Should not allow to withdraw from empty account

Given an account REST service
And an empty account
When I POST to /account/withdraw amount 1
Then 403 status code is returned  