package org.example.banks.domain.transaction;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

import java.util.Random;

@Getter
@Setter
public class Withdrawal implements Transaction {

    public Withdrawal() {
        var rand = new Random();
        id = (long) rand.nextInt(1, 1000000);
    }

    private Long id;

    private double amount;

    private Integer day;
    private Type type;
    private Status status = Status.VALID;

    private Long accountFrom;
    private Long accountTo;

    public void cancelTransaction() {
        amount = -amount;
        status = Status.CANCELLED;
    }

    public void setCancelStatus() {
        status = Status.CANCELLED;
    }

    public void setFlagCancelled() {
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
        var rand = new Random();
        id = (long) rand.nextInt(1, 1000000);
    }

    public boolean isCancelled() {
        return status == Status.CANCELLED;
    }


    public double handleRateFees(double currentBalance, Account account) {
        return currentBalance - amount;
    }

    public Long getSenderAccountId() {
        return accountFrom;
    }


    public Long getReceiverAccountId() {
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
