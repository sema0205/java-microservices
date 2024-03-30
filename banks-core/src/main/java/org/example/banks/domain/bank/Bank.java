package org.example.banks.domain.bank;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.user.User;

import java.util.HashMap;
import java.util.UUID;


public interface Bank {

    void addNotifyUser(
            Notification notification,
            User user
    );

    double findSuiteInterestRate(
            double amount
    );

    void notifyUsers();

    void handleAccrual(
            Account account
    );

    UUID getId();

    double getCommission();

    double getFixedInterest();

    double getCreditLimit();

    HashMap<UUID, User> getUsers();
}
