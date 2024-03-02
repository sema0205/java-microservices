package org.example.banks.domain.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Interest {

    private double rate;

    private double amountFrom;
    private double amountUpTo;

}
