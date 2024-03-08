package org.example.banks.domain.bank;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.user.User;

import java.util.HashMap;


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

    Long getId();

    double getCommission();

    double getFixedInterest();

    double getCreditLimit();

    HashMap<Long, User> getUsers();
}
