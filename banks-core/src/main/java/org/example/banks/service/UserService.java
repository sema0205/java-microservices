package org.example.banks.service;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.user.User;

public interface UserService {

    /**
     * init transaction
     *
     * @param transaction entity
     * @return return account entity
     */
    Account makeTransaction(
            Transaction transaction
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
     * update user fields
     *
     * @param user entity
     * @return return user entity
     */
    User update(
            User user
    );

}
