package org.example.banks.domain.transaction;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.IAccount;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

import java.util.Random;

@Getter
@Setter
public class Transfer implements ITransaction {

    private Long id;

    private double amount;

    private Integer day;
    private Type type;
    private Status status = Status.VALID;

    private Long accountFrom;
    private Long accountTo;

    public void cancelTransaction() {
        var accountToSwap = accountTo;
        accountTo = accountFrom;
        accountFrom = accountToSwap;

        status = Status.CANCELLED;
    }

    public void setCancelStatus() {
        status = Status.CANCELLED;
    }

    public void setFlagCancelled() {
        status = Status.CANCELLED;
    }

    public void setSender(Long id) {
        accountFrom = id;
    }

    public void setReceiver(Long id) {
        accountTo = id;
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
        id = (long) rand.nextInt(1, 50);
    }

    public boolean isCancelled() {
        return status == Status.CANCELLED;
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


    public IAccount initTransaction(User sender, User receiver) {
        var accountFrom = sender.getAccount();
        var accountTo = receiver.getAccount();

        accountFrom.addTransaction(this);
        accountTo.addTransaction(this);

        if (sender.isFlagged()) {
            status = Status.CANCELLED;
            return accountFrom;
        }

        accountFrom.decreaseBalance(this);
        accountTo.increaseBalance(this);

        return accountFrom;
    }


    public double handleRateFees(double currentBalance, IAccount account) {
        if (accountFrom.equals(id)) {
            account.increaseBalance(this);
            return currentBalance + amount;
        } else {
            return currentBalance - amount;
        }
    }

}