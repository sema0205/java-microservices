package org.example.banks.domain.account;

import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.transaction.Transaction;

public interface Account {

    Long getId();

    double getBalance();


    void addTransaction(
            Transaction transaction
    );

    void increaseBalance(
            Transaction transaction
    );

    boolean decreaseBalance(
            Transaction transaction
    );

    double handleRateFees(
            double currentBalance,
            Transaction transaction
    );

    void accrualFees(
            Bank bank
    );

}

