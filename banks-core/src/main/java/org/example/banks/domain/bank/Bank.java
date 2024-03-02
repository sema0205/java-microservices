package org.example.banks.domain.bank;


import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.user.User;

import java.util.*;

@Getter
@Setter
public class Bank {

    private Long id;

    private double commission;

    private double fixedInterest;

    private double creditLimit;

    private List<Interest> interest = new ArrayList<>();

    private HashMap<Long, User> users = new HashMap<>();

    private HashMap<Notification, List<User>> notifications = new HashMap<>();

    public void addNotifyUser(
            Notification notification,
            User user
    ) {
        var notifyList = notifications.get(notification);

        if (notifyList == null) {
            notifyList = new ArrayList<>();
        }

        notifyList.add(user);
        notifications.put(notification, notifyList);
    }

    public double findSuiteInterestRate(
            double amount
    ) {
        for (Interest interest : interest) {
            if (interest.isSuitableAmount(amount)) {
                return interest.getRate();
            }
        }

        return interest.getFirst().getRate();
    }

    public void notifyUsers() {
        for (Map.Entry<Notification, List<User>> entry : notifications.entrySet()) {
            var users = entry.getValue();
            var notification = entry.getKey();
            for (User user : users) {
                user.addNotification(notification);
            }
        }
    }

    public void accrualDeposit(
            Account account
    ) {
        var transactions = account.getTransactions();
        transactions.sort(Comparator.comparingInt(Meta::getDay));

        if (transactions.isEmpty()) {
            return;
        }

        var fixedInterestValue = fixedInterest / 365 / 100;
        double currentBalance = 0;

        for (Meta meta : transactions) {
            var tx = meta.getTransaction();

            if (tx.isCancelled())
                continue;

            currentBalance = account.handleRateFees(currentBalance, tx);

            currentBalance += (currentBalance * fixedInterestValue);
        }

        account.setBalance(currentBalance);
    }

    public void accrualDebit(
            Account account
    ) {
        var transactions = account.getTransactions();
        transactions.sort(Comparator.comparingInt(Meta::getDay));

        if (transactions.isEmpty()) {
            return;
        }

        var fixedInterestValue = fixedInterest / 365 / 100;
        double currentBalance = 0;

        for (Meta meta : transactions) {
            var tx = meta.getTransaction();

            if (tx.isCancelled())
                continue;

            currentBalance = account.handleRateFees(currentBalance, tx);

            currentBalance += (currentBalance * fixedInterestValue);
        }

        account.setBalance(currentBalance);
    }

    public void accrualCredit(
            Account account
    ) {
        var transactions = account.getTransactions();
        transactions.sort(Comparator.comparingInt(Meta::getDay));

        if (transactions.isEmpty()) {
            return;
        }

        var commissionValue = commission / 365 / 100;
        double currentBalanceValue = creditLimit;

        for (Meta meta : transactions) {
            var tx = meta.getTransaction();
            if (tx.isCancelled())
                continue;

            currentBalanceValue = account.handleRateFees(currentBalanceValue, tx);

            if (currentBalanceValue < 0) {
                currentBalanceValue -= (currentBalanceValue * commissionValue);
            }
        }

        account.setBalance(currentBalanceValue);
    }

    public void handleAccrual(
            Account account
    ) {
        switch (account.getType()) {
            case DEBIT:
                accrualDebit(account);
                break;
            case DEPOSIT:
                accrualDeposit(account);
                break;
            case CREDIT:
                accrualCredit(account);
                break;
        }
    }

}
