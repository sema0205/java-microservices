package org.example.banks.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.account.Account;

@Getter
@Setter
public class User {

    private Long id;

    private String name;
    private String lastName;

    private Status status = Status.WORKING;

    private String address;
    private String passportData;

    private Account account;
}
