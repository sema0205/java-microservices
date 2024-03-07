package org.example.banks.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private HashMap<Long, User> userTable = new HashMap<>();

    public User getById(
            Long id
    ) {
        return userTable.get(id);
    }

    public User update(
            User user
    ) {
        user.validateData();
        return userTable.put(user.getId(), user);
    }

    public User create(
            User user
    ) {
        user.validateData();
        return userTable.put(user.getId(), user);
    }

    public User getAccountOwner(
            Long accountId
    ) {
        User result = null;

        for (Map.Entry<Long, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (Objects.equals(user.getAccount().getId(), accountId)) {
                result = user;
                break;
            }
        }

        return result;
    }

    public HashMap<Long, User> getAll() {
        return userTable;
    }

}

