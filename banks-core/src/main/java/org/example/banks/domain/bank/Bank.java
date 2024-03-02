package org.example.banks.domain.bank;


import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
}
