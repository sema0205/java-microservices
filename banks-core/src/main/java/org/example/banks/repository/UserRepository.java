package org.example.banks.repository;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.user.User;

import java.util.HashMap;

public interface UserRepository {
    /**
     * get user entity by id
     *
     * @param id user id
     * @return user entity
     */
    User getById(
            Long id
    );

    /**
     * update user fields
     *
     * @param user entity
     * @return return user entity
     */
    User update(
            User user
    );

    /**
     * create user
     *
     * @param user entity
     * @return user entity
     */
    User create(
            User user
    );

    /**
     * get user from account id
     *
     * @param accountId id of an account
     * @return user entity
     */
    User getAccountOwner(
            Long accountId
    );

    /**
     * init transaction in any type
     *
     * @param transaction is tx entity
     * @return account entity
     */
    Account makeTransaction(
            Transaction transaction
    );

    /**
     * get all users info
     *
     * @return map of users
     */
    HashMap<Long, User> getAll();

}
