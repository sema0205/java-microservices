package org.example.banks.service;

import org.example.banks.domain.account.IAccount;
import org.example.banks.domain.transaction.ITransaction;
import org.example.banks.domain.user.User;

public interface UserService {

    /**
     * init transaction
     *
     * @param transaction entity
     * @return return account entity
     */
    IAccount makeTransaction(
            ITransaction transaction
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
