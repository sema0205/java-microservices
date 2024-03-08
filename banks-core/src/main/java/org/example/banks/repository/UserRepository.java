package org.example.banks.repository;

import org.example.banks.domain.user.User;

import java.util.HashMap;
import java.util.UUID;

public interface UserRepository {
    /**
     * get user entity by id
     *
     * @param id user id
     * @return user entity
     */
    User getById(
            UUID id
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
            UUID accountId
    );

    /**
     * get all users info
     *
     * @return map of users
     */
    HashMap<UUID, User> getAll();

}
