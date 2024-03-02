package org.example.banks.service;

import org.example.banks.domain.bank.Bank;

public interface CentralBankService {

    /**
     * register new bank
     *
     * @param bank entity
     * @return return bank entity
     */
    Bank registerBank(
            Bank bank
    );

    /**
     * init starting paying to client, taking fees
     */
    void notifyBanks();
}
