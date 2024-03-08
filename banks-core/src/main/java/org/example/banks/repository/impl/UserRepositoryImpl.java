package org.example.banks.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private HashMap<UUID, User> userTable = new HashMap<>();

    public User getById(
            UUID id
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
            UUID accountId
    ) {
        User result = null;

        for (Map.Entry<UUID, User> entry : userTable.entrySet()) {
            User user = entry.getValue();
            if (Objects.equals(user.getAccount().getId(), accountId)) {
                result = user;
                break;
            }
        }

        return result;
    }

    public HashMap<UUID, User> getAll() {
        return userTable;
    }

}

