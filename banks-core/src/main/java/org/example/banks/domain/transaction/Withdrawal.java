package org.example.banks.domain.transaction;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

import java.util.UUID;

@Getter
@Setter
public class Withdrawal implements Transaction {

    public Withdrawal() {
        id = UUID.randomUUID();
    }

    private UUID id;

    private double amount;

    private Integer day;
    private Type type;
    private Status status = Status.VALID;

    private UUID accountFrom;
    private UUID accountTo;

    public void cancelTransaction() {
        amount = -amount;
        status = Status.CANCELLED;
    }

    public Meta getMeta() {
        var meta = new Meta();
        meta.setId(id);
        meta.setTransaction(this);
        meta.setDay(day);

        return meta;
    }

    public void setRandomId() {
        id = UUID.randomUUID();
    }

    public boolean isCancelled() {
        return status == Status.CANCELLED;
    }


    public double handleRateFees(double currentBalance, Account account) {
        return currentBalance - amount;
    }

    public UUID getSenderAccountId() {
        return accountFrom;
    }


    public UUID getReceiverAccountId() {
        return accountTo;
    }


    public double getTransactionAmount() {
        return amount;
    }


    public Account initTransaction(User sender, User receiver) {
        var account = sender.getAccount();
        account.addTransaction(this);

        if (sender.isFlagged()) {
            status = Status.CANCELLED;
            return account;
        }

        account.decreaseBalance(this);
        return account;
    }

}
