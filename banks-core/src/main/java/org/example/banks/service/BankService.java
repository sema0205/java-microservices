package org.example.banks.service;

import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.IBank;
import org.example.banks.domain.bank.Notification;
import org.example.banks.domain.transaction.ITransaction;
import org.example.banks.domain.user.User;

public interface BankService {

    /**
     * register new user
     *
     * @param user entity
     * @param bank entity
     * @return user entity
     */
    User registerUser(
            User user,
            IBank bank
    );

    /**
     * update user fields
     *
     * @param user entity
     * @return user entity
     */
    User updateUser(
            User user
    );

    /**
     * update bank fields
     *
     * @param bank entity
     * @return bank entity
     */
    IBank update(
            IBank bank
    );

    /**
     * cancel tx
     *
     * @param transaction entity
     * @return tx entity
     */
    ITransaction cancelTransaction(
            ITransaction transaction
    );


    /**
     * add new notify for user
     *
     * @param notification notify type
     * @param user user entity
     * @param bank bank entity
     * @return bank entity
     */
    IBank addNotifyUser(
            Notification notification,
            User user,
            IBank bank
    );

}
