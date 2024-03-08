package org.example.banks.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;
import org.example.banks.service.UserService;

@RequiredArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public Account makeTransaction(Transaction transaction) {
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
