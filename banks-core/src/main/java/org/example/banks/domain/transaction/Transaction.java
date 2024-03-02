package org.example.banks.domain.transaction;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;

import java.util.Random;

import static org.example.banks.domain.transaction.Type.TRANSFER;
import static org.example.banks.domain.transaction.Type.WITHDRAWAL;
import static org.example.banks.domain.transaction.Type.DEPOSIT;

@Getter
@Setter
public class Transaction {

    private Long id;

    private double amount;

    private Integer day;
    private Type type;
    private Status status = Status.VALID;

    private Long accountFrom;
    private Long accountTo;

    public void cancelTransaction() {
        switch (type) {
            case TRANSFER:
                var accountToSwap = accountTo;
                accountTo = accountFrom;
                accountFrom = accountToSwap;
                break;
            case WITHDRAWAL, DEPOSIT:
                amount = -amount;
                break;
        }

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
        id = (long) rand.nextInt(1, 50);
    }

    public boolean isCancelled() {
        return status == Status.CANCELLED;
    }

}
