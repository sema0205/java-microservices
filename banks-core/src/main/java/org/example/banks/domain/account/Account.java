package org.example.banks.domain.account;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Account {

    private Long id;

    private double balance;

    private Type type = Type.DEBIT;

    private List<Meta> transactions = new ArrayList<>();

}
