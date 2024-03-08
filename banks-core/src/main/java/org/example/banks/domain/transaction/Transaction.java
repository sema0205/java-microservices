package org.example.banks.domain.transaction;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

import java.util.UUID;

public interface Transaction {

    void cancelTransaction();

    void setAmount(double txAmount);

    Meta getMeta();

    void setRandomId();

    boolean isCancelled();

    double handleRateFees(
            double currentBalance,
            Account account
    );

    UUID getSenderAccountId();

    UUID getReceiverAccountId();

    double getTransactionAmount();

    Account initTransaction(
            User sender,
            User receiver
    );

}
