package org.example.banks.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.IAccount;
import org.example.banks.domain.bank.Notification;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {

    private Long id;

    private String name;
    private String lastName;

    private Status status = Status.WORKING;

    private String address;
    private String passportData;

    private IAccount account;

    private List<Notification> notifications = new ArrayList<>();

    public void validateData() {
        if (address != null && passportData != null) {
            status = Status.WORKING;
        } else {
            status = Status.FLAGGED;
        }
    }

    public void addNotification(
            Notification notification
    ) {
        notifications.add(notification);
    }

    public boolean isFlagged(){
        return status == Status.FLAGGED;
    }

}
