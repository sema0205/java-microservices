package org.example.banks.domain.account;

import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.transaction.Transaction;

import java.util.UUID;


/**
 * Meta info of transaction to store in account
 */
@Getter
@Setter
public class Meta {

    private UUID id;

    private Integer day;

    private Transaction transaction;

}
