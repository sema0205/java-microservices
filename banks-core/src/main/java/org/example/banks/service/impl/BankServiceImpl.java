package org.example.banks.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.Notification;
import org.example.banks.domain.transaction.Status;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.user.User;
import org.example.banks.repository.BankRepository;
import org.example.banks.repository.UserRepository;
import org.example.banks.service.BankService;
import org.example.banks.service.UserService;

import java.util.ArrayList;


@AllArgsConstructor
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;
    private UserService userService;

    public User registerUser(
            User user,
            Bank bank
    ) {
        var users = bank.getUsers();
        users.put(user.getId(), user);

        return userService.create(user);
    }


    public User updateUser(
            User user
    ) {
        return userService.update(user);
    }


    public Bank update(
            Bank bank
    ) {
        return bankRepository.update(bank);
    }

    public Transaction cancelTransaction(
            Transaction transaction
    ) {
        transaction.cancelTransaction();
        switch (transaction.getType()) {
            case DEPOSIT:
                userService.makeDepositTransaction(transaction);
                break;
            case WITHDRAWAL:
                userService.makeWithdrawalTransaction(transaction);
                break;
            case TRANSFER:
                userService.makeTransferTransaction(transaction);
                break;
        }

        return transaction;
    }

    public Bank addNotifyUser(
            Notification notification,
            User user,
            Bank bank
    ) {
        bank.addNotifyUser(notification, user);
        return bank;
    }

}
