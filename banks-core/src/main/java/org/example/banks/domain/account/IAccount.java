package org.example.banks.domain.account;

import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.IBank;
import org.example.banks.domain.transaction.ITransaction;

public interface IAccount {

    Long getId();

    double getBalance();


    void addTransaction(
            ITransaction transaction
    );

    void increaseBalance(
            ITransaction transaction
    );

    void decreaseBalance(
            ITransaction transaction
    );

    double handleRateFees(
            double currentBalance,
            ITransaction transaction
    );

    void accrualFees(
            IBank bank
    );

}

