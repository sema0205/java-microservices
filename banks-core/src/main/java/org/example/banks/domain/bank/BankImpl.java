package org.example.banks.domain.bank;


import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.user.User;

import java.util.*;

@Getter
@Setter
public class BankImpl implements Bank {

    public BankImpl() {
        var rand = new Random();
        id = (long) rand.nextInt(1, 150);
    }

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


    public void handleAccrual(
            Account account
    ) {
        account.accrualFees(this);
    }

}
