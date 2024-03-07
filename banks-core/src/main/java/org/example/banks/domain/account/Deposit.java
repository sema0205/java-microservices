package org.example.banks.domain.account;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.IBank;
import org.example.banks.domain.transaction.ITransaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class Deposit implements IAccount {

    private Long id;

    private double balance;

    private List<Meta> transactions = new ArrayList<>();

    public void addTransaction(
            ITransaction transaction
    ) {
        transaction.setRandomId();
        var meta = transaction.getMeta();
        transactions.add(meta);
    }

    public void increaseBalance(
            ITransaction transaction
    ) {
        balance += transaction.getTransactionAmount();
    }

    public void decreaseBalance(
            ITransaction transaction
    ) {
        balance -= transaction.getTransactionAmount();
    }

    public double handleRateFees(
            double currentBalance,
            ITransaction transaction
    ) {
        return transaction.handleRateFees(currentBalance, this);
    }


    public void accrualFees(IBank bank) {
        var accountTransactions = transactions;
        accountTransactions.sort(Comparator.comparingInt(Meta::getDay));

        if (accountTransactions.isEmpty()) {
            return;
        }

        var fixedInterestValue = bank.getFixedInterest() / 365 / 100;
        double currentBalance = 0;

        for (Meta meta : accountTransactions) {
            var tx = meta.getTransaction();

            if (tx.isCancelled())
                continue;

            currentBalance = handleRateFees(currentBalance, tx);

            currentBalance += (currentBalance * fixedInterestValue);
        }

        balance = currentBalance;
    }

}
