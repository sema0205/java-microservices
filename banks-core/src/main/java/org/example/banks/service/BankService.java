package org.example.banks.service;

import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.Notification;
import org.example.banks.domain.transaction.Transaction;
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
            Bank bank
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
    Bank update(
            Bank bank
    );

    /**
     * cancel tx
     *
     * @param transaction entity
     * @return tx entity
     */
    Transaction cancelTransaction(
            Transaction transaction
    );


    /**
     * add new notify for user
     *
     * @param notification notify type
     * @param User user entity
     * @param bank bank entity
     * @return bank entity
     */
    Bank addNotifyUser(
            Notification notification,
            User User,
            Bank bank
    );
}
