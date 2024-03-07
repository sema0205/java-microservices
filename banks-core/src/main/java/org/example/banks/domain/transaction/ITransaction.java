package org.example.banks.domain.transaction;

import org.example.banks.domain.account.IAccount;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

public interface ITransaction {

    void cancelTransaction();

    void setCancelStatus();

    void setFlagCancelled();

    void setAmount(double txAmount);

    Meta getMeta();

    void setRandomId();

    boolean isCancelled();

    double handleRateFees(
            double currentBalance,
            IAccount account
    );

    Long getSenderAccountId();

    Long getReceiverAccountId();

    double getTransactionAmount();

    IAccount initTransaction(
            User sender,
            User receiver
    );

}
