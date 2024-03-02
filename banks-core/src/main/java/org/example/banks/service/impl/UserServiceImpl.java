package org.example.banks.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.user.Status;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;
import org.example.banks.service.UserService;

import java.util.Random;

@RequiredArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public Account makeDepositTransaction(Transaction transaction) {
        var user = userRepository.getById(transaction.getAccountFrom());

        var account = user.getAccount();
        account.addTransaction(transaction);

        if (user.getStatus().equals(Status.FLAGGED)) {
            transaction.setCancelStatus();
            return account;
        }

        account.increaseBalance(transaction);

        return account;
    }


    public Account makeWithdrawalTransaction(Transaction transaction) {
        var user = userRepository.getById(transaction.getAccountFrom());

        var account = user.getAccount();
        account.addTransaction(transaction);

        if (user.getStatus().equals(Status.FLAGGED)) {
            transaction.setCancelStatus();
            return account;
        }

        account.decreaseBalance(transaction);

        return account;
    }


    public Account makeTransferTransaction(Transaction transaction) {
        var userFrom = userRepository.getById(transaction.getAccountFrom());
        var userTo = userRepository.getById(transaction.getAccountTo());

        var accountFrom = userFrom.getAccount();
        accountFrom.addTransaction(transaction);

        var accountTo = userTo.getAccount();
        accountTo.addTransaction(transaction);

        if (userFrom.getStatus().equals(Status.FLAGGED)) {
            transaction.setCancelStatus();
            return accountFrom;
        }

        accountFrom.decreaseBalance(transaction);
        accountTo.increaseBalance(transaction);

        return accountFrom;
    }


    public User create(User user) {
        return userRepository.create(user);
    }


    public User update(User user) {
        return userRepository.update(user);
    }


}
