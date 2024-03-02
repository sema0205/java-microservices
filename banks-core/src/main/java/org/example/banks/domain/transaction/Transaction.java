package org.example.banks.domain.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long id;

    private double amount;

    private Integer day;
    private Type type;
    private Status status = Status.VALID;

    private Long accountFrom;
    private Long accountTo;
}
