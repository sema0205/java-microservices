package org.example.banks.domain.account;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Debit implements Account {

    public Debit() {
        var rand = new Random();
        id = (long) rand.nextInt(1, 150);
    }

    private Long id;

    private double balance;

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
        balance += transaction.getTransactionAmount();
    }

    public boolean decreaseBalance(
            Transaction transaction
    ) {
        if (balance - transaction.getTransactionAmount() < 0) {
            return false;
        } else {
            balance -= transaction.getTransactionAmount();
            return true;
        }
    }

    public double handleRateFees(
            double currentBalance,
            Transaction transaction
    ) {
        return transaction.handleRateFees(currentBalance, this);
    }


    public void accrualFees(Bank bank) {
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
