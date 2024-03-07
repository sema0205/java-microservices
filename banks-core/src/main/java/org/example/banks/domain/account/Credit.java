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
public class Credit implements IAccount {

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

    public void accrualFees(
            IBank bank
    ) {
        var accountTransactions = transactions;
        accountTransactions.sort(Comparator.comparingInt(Meta::getDay));

        if (accountTransactions.isEmpty()) {
            return;
        }

        var commissionValue = bank.getCommission() / 365 / 100;
        double currentBalanceValue = bank.getCreditLimit();

        for (Meta meta : accountTransactions) {
            var tx = meta.getTransaction();
            if (tx.isCancelled())
                continue;

            currentBalanceValue = handleRateFees(currentBalanceValue, tx);

            if (currentBalanceValue < 0) {
                currentBalanceValue -= (currentBalanceValue * commissionValue);
            }
        }

        balance = currentBalanceValue;
    }

}
