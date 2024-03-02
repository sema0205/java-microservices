package org.example.banks.repository.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.user.Status;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;

import java.util.*;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private HashMap<Long, User> userTable = new HashMap<>();

    public User getById(
            Long id
    ) {
        return userTable.get(id);
    }

    public User update(
            User user
    ) {
        if (user.getAddress() != null && user.getPassportData() != null) {
            user.setStatus(Status.WORKING);
        }

        return userTable.put(user.getId(), user);
    }

    public User create(
            User user
    ) {
        if (user.getAddress() == null || user.getPassportData() == null) {
            user.setStatus(Status.FLAGGED);
        }

        return userTable.put(user.getId(), user);
    }

    public User getAccountOwner(
            Long accountId
    ) {
        User result = null;

        for (Map.Entry<Long, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (Objects.equals(user.getAccount().getId(), accountId)) {
                result = user;
                break;
            }
        }

        return result;
    }


    public Account makeTransaction(
            Transaction transaction
    ) {

        var user = userTable.get(transaction.getAccountFrom());
        var account = user.getAccount();
        var balance = account.getBalance();
        var transactions = account.getTransactions();

        var rand = new Random();
        transaction.setId(rand.nextLong());

        var meta = new Meta();
        meta.setId(transaction.getId());
        meta.setTransaction(transaction);
        meta.setDay(transaction.getDay());

        if (user.getStatus().equals(Status.FLAGGED)) {
            transaction.setStatus(org.example.banks.domain.transaction.Status.CANCELLED);
            transactions.add(meta);
            return account;
        }

        switch (transaction.getType()) {
            case WITHDRAWAL:
                account.setBalance(balance - transaction.getAmount());
                transactions.add(meta);
                break;
            case DEPOSIT:
                account.setBalance(balance + transaction.getAmount());
                transactions.add(meta);
                break;
            case TRANSFER:
                var accountFrom = user.getAccount();
                var balanceFrom = account.getBalance();
                var transactionsAccountFrom = account.getTransactions();

                var accountTo = userTable.get(transaction.getAccountTo()).getAccount();
                var balanceTo = accountTo.getBalance();
                var transactionsAccountTo = accountTo.getTransactions();

                accountFrom.setBalance(balanceFrom - transaction.getAmount());
                accountTo.setBalance(balanceTo + transaction.getAmount());

                transactionsAccountFrom.add(meta);
                transactionsAccountTo.add(meta);
                break;
        }

        return account;
    }


    public HashMap<Long, User> getAll() {
        return userTable;
    }

}

