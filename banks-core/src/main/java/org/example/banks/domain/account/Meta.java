package org.example.banks.domain.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.banks.domain.transaction.Transaction;


/**
 * Meta info of transaction to store in account
 */
@Getter
@Setter
public class Meta {

    private Long id;
    private Integer day;
    private Transaction transaction;

}
