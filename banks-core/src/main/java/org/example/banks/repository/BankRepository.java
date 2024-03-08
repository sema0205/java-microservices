package org.example.banks.repository;

import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.user.User;

import java.util.HashMap;
import java.util.UUID;

public interface BankRepository {
    /**
     * get bank meta info
     *
     * @param id is bank id
     * @return bank entity
     */
    Bank getById(
            UUID id
    );

    /**
     * update bank meta info
     *
     * @param bank entity
     * @return bank entity
     */
    Bank update(
            Bank bank
    );

    /**
     * create new bank
     *
     * @param bank entity
     * @return bank entity
     */
    Bank create(
            Bank bank
    );

    /**
     * get user meta info
     *
     * @param userId id of a user
     * @return user entity
     */
    User getUserById(
            UUID userId
    );

    /**
     * start charging commissions and giving back percentage on deposits
     */
    void initCharge();

    /**
     * get all banks info
     *
     * @return map of banks
     */
    HashMap<UUID, Bank> getAll();

}

