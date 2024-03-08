package org.example.banks.domain.transaction;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

public interface Transaction {

    void cancelTransaction();

    void setCancelStatus();

    void setFlagCancelled();

    void setAmount(double txAmount);

    Meta getMeta();

    void setRandomId();

    boolean isCancelled();

    double handleRateFees(
            double currentBalance,
            Account account
    );

    Long getSenderAccountId();

    Long getReceiverAccountId();

    double getTransactionAmount();

    Account initTransaction(
            User sender,
            User receiver
    );

}
