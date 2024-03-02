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

import java.util.ArrayList;


@AllArgsConstructor
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;
    private UserRepository userRepository;

    public User registerUser(
            User user,
            Bank bank
    ) {
        var users = bank.getUsers();
        users.put(user.getId(), user);

        return userRepository.create(user);
    }


    public User updateUser(
            User user
    ) {
        return userRepository.update(user);
    }


    public Bank update(
            Bank bank
    ) {
        return bankRepository.update(bank);
    }

    public Transaction cancelTransaction(
            Transaction transaction
    ) {
        var accountFrom = transaction.getAccountFrom();
        var accountTo = transaction.getAccountTo();
        var amount = transaction.getAmount();

        switch (transaction.getType()) {
            case TRANSFER:
                transaction.setAccountFrom(accountTo);
                transaction.setAccountTo(accountFrom);
                break;
            case WITHDRAWAL:
                transaction.setAmount(amount);
                break;
            case DEPOSIT:
                transaction.setAmount(-amount);
                break;
        }

        userRepository.makeTransaction(transaction);
        transaction.setStatus(Status.CANCELLED);

        return transaction;
    }

    public Bank addNotifyUser(
            Notification notification,
            User User,
            Bank bank
    ) {
        var notifyMeta = bank.getNotifications();
        var notifyList = notifyMeta.get(notification);
        if (notifyList == null) {
            notifyList = new ArrayList<>();
        }

        notifyList.add(User);
        notifyMeta.put(notification, notifyList);

        return bank;
    }

}
