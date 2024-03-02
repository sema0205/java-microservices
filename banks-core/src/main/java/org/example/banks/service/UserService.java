package org.example.banks.service;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.transaction.Transaction;

public interface UserService {

    /**
     * init transaction in any type
     *
     * @param transaction entity
     * @return return account entity
     */
    Account makeTransaction(
            Transaction transaction
    );

}
