package org.example.banks.domain.account;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.bank.Notification;
import org.example.banks.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Account {

    private Long id;

    private double balance;

    private Type type = Type.DEBIT;

    private List<Meta> transactions = new ArrayList<>();

    public void addTransaction(
            Transaction transaction
    ) {
        transaction.setRandomId();
        var meta = transaction.getMeta();
        transactions.add(meta);
    }

    public void increaseBalance(
            Transaction transaction
    ) {
        balance += transaction.getAmount();
    }

    public void decreaseBalance(
            Transaction transaction
    ) {
        balance -= transaction.getAmount();
    }

    public double handleRateFees(
            double currentBalance,
            Transaction transaction
    ) {
        switch (transaction.getType()) {
            case DEPOSIT:
                return currentBalance += transaction.getAmount();
            case WITHDRAWAL:
                return currentBalance -= transaction.getAmount();
            case TRANSFER:
                if (transaction.getAccountTo().equals(id)) {
                    increaseBalance(transaction);
                    return currentBalance += transaction.getAmount();
                } else {
                    return currentBalance -= transaction.getAmount();
                }
        }

        return 0;
    }

}
