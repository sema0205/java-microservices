package org.example.banks.repository;

import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.user.User;

import java.util.HashMap;

public interface BankRepository {
    /**
     * get bank meta info
     *
     * @param id is bank id
     * @return bank entity
     */
    Bank getById(
            Long id
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
            Long userId
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
    HashMap<Long, Bank> getAll();

}

