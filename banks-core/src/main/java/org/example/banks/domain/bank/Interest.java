package org.example.banks.domain.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Diff ranges of rates for specific bank
 */
@Getter
@Setter
@AllArgsConstructor
public class Interest {

    private double rate;

    private double amountFrom;
    private double amountUpTo;


    public boolean isSuitableAmount(
            double amount
    ) {
        return amount >= amountFrom && amount <= amountUpTo;
    }

}
