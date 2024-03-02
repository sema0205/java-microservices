package org.example.banks.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.repository.UserRepository;
import org.example.banks.service.UserService;

@RequiredArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public Account makeTransaction(
            Transaction transaction
    ) {
        return userRepository.makeTransaction(transaction);
    }
}
