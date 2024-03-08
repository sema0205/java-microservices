package org.example.banks.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.user.User;
import org.example.banks.repository.BankRepository;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class BankRepositoryImpl implements BankRepository {

    private HashMap<Long, Bank> bankTable = new HashMap<>();

    public Bank getById(
            Long id
    ) {
        return bankTable.get(id);
    }

    public Bank update(
            Bank bank
    ) {
        return bankTable.put(bank.getId(), bank);
    }

    public Bank create(
            Bank bank
    ) {
        return bankTable.put(bank.getId(), bank);
    }

    public User getUserById(
            Long userId
    ) {
        User result = null;

        for (Map.Entry<Long, Bank> entry : bankTable.entrySet()) {
            var bank = entry.getValue();

            if (bank.getUsers().get(userId) != null) {
                result = bank.getUsers().get(userId);
                break;
            }
        }

        return result;
    }

    public void initCharge() {
        for (Map.Entry<Long, Bank> entryBank : bankTable.entrySet()) {
            var bank = entryBank.getValue();
            var users = bank.getUsers();

            for (Map.Entry<Long, User> entryUser : users.entrySet()) {
                var user = entryUser.getValue();
                bank.handleAccrual(user.getAccount());
            }

        }
    }

    public HashMap<Long, Bank> getAll() {
        return bankTable;
    }

}
