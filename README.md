# Console ATM Simulator

A menu-driven ATM written in core Java (no libraries). Log in with an account number and PIN, then check balance, deposit, withdraw, transfer to another account, and print a statement.

## Concepts shown
- Object-oriented design (`Account`, `Bank`, `Transaction`)
- Custom checked exception (`InsufficientFundsException`)
- Input validation and PIN lockout after 3 attempts
- Java records, switch expressions, collections

## Run it (Java 17 or newer)
```bash
cd 1-bank-atm-simulator
mkdir out
javac -d out src/com/manaswini/bank/*.java
java -cp out com.manaswini.bank.Main
```
Demo logins: `1001 / 1234` and `1002 / 4321`.

## Ideas to extend
- Save accounts to a file or MySQL with JDBC
- Add account creation and PIN change
- Write JUnit tests for `Account`
