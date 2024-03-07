package org.example.banks.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.account.IAccount;
import org.example.banks.domain.transaction.ITransaction;
import org.example.banks.domain.user.Status;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;
import org.example.banks.service.UserService;

@RequiredArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public IAccount makeTransaction(ITransaction transaction) {
        var userFrom = userRepository.getById(transaction.getSenderAccountId());
        var userTo = userRepository.getById(transaction.getReceiverAccountId());

        return transaction.initTransaction(userFrom, userTo);
    }

    public User create(User user) {
        return userRepository.create(user);
    }


    public User update(User user) {
        return userRepository.update(user);
    }


}
