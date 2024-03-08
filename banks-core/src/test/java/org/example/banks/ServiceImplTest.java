package org.example.banks;


import org.example.banks.domain.account.Debit;
import org.example.banks.domain.bank.BankImpl;
import org.example.banks.domain.bank.Interest;
import org.example.banks.domain.bank.Notification;
import org.example.banks.domain.transaction.Deposit;
import org.example.banks.domain.user.User;
import org.example.banks.repository.BankRepository;
import org.example.banks.repository.UserRepository;
import org.example.banks.repository.impl.BankRepositoryImpl;
import org.example.banks.repository.impl.UserRepositoryImpl;
import org.example.banks.service.BankService;
import org.example.banks.service.CentralBankService;
import org.example.banks.service.UserService;
import org.example.banks.service.impl.BankServiceImpl;
import org.example.banks.service.impl.CentralBankServiceImpl;
import org.example.banks.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ServiceImplTest {

    @Test
    void cancelTransaction() {

        BankRepository bankRepository = new BankRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        BankService bankService = new BankServiceImpl(bankRepository, userService);

        var bank = new BankImpl();

        var user = new User();
        user.setPassportData("passort");
        user.setAddress("address");
        var account = new Debit();
        user.setAccount(account);

        var transaction = new Deposit();
        transaction.setAmount(50);
        transaction.setAccountFrom(user.getId());
        bankRepository.create(bank);
        bankService.registerUser(user, bank);

        userService.makeTransaction(transaction);

        Assertions.assertEquals(50, account.getBalance());
        bankService.cancelTransaction(transaction);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    void addNotification() {
        BankRepository bankRepository = new BankRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        BankService bankService = new BankServiceImpl(bankRepository, userService);

        BankImpl bank = new BankImpl();

        var user = new User();
        var account = new Debit();
        user.setAccount(account);

        var notification = Notification.CREDIT_CARD_CHANGE;

        bankRepository.create(bank);
        bankService.registerUser(user, bank);


        Assertions.assertEquals(true, bank.getNotifications().isEmpty());
        bankService.addNotifyUser(notification, user, bank);
        Assertions.assertEquals(false, bank.getNotifications().isEmpty());

    }

    @Test
    void registerUser() {
        BankRepository bankRepository = new BankRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        BankService bankService = new BankServiceImpl(bankRepository, userService);

        BankImpl bank = new BankImpl();

        var user = new User();
        var account = new Debit();
        user.setAccount(account);

        bankRepository.create(bank);

        Assertions.assertEquals(true, bank.getUsers().isEmpty());
        bankService.registerUser(user, bank);
        Assertions.assertEquals(false, bank.getUsers().isEmpty());

    }

    @Test
    void registerBank() {
        var bankRepository = new BankRepositoryImpl();
        var centralBankService = new CentralBankServiceImpl(bankRepository);

        var random = new Random();

        BankImpl bank = new BankImpl();
        bank.setId(random.nextLong());

        Assertions.assertEquals(null, bankRepository.getById(bank.getId()));
        centralBankService.registerBank(bank);
        Assertions.assertEquals(bank, bankRepository.getById(bank.getId()));

    }

    @Test
    void getFixedRate() {
        BankRepository bankRepository = new BankRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        BankService bankService = new BankServiceImpl(bankRepository, userService);
        CentralBankService centralBankService = new CentralBankServiceImpl(bankRepository);

        var random = new Random();

        var defaultBank = new BankImpl();
        defaultBank.setId(1L);
        defaultBank.setCommission(4);
        defaultBank.setFixedInterest(6);
        defaultBank.setCreditLimit(50000);

        var interestArrayList = new ArrayList<Interest>();
        interestArrayList.add(new Interest(2, 0, 40000));
        interestArrayList.add(new Interest(4, 40000, 100000));
        interestArrayList.add(new Interest(6, 100000, 1000000));
        defaultBank.setInterest(interestArrayList);

        centralBankService.registerBank(defaultBank);


        {
            var user = new User();
            user.setId(1L);
            user.setName("lesha");
            user.setLastName("vertov");
            user.setAddress("lenina 23");
            user.setPassportData("1324224");
            var bank = bankRepository.getById(1L);

            var account = new Debit();
            account.setId((long) random.nextInt(1, 15));
            user.setAccount(account);

            bankService.registerUser(user, bank);

            for (int i = 0; i < 70; i++) {
                var tx = new Deposit();
                tx.setId((long) random.nextInt(1, 50));
                tx.setType(org.example.banks.domain.transaction.Type.DEPOSIT);
                tx.setAmount(5);
                tx.setDay(i);
                tx.setAccountFrom(user.getId());
                userService.makeTransaction(tx);
            }

        }

        {
            var user = new User();
            user.setId(2L);
            user.setName("vadim");
            user.setLastName("petrov");
            user.setAddress("pobeda 63");
            user.setPassportData("4242");
            var bank = bankRepository.getById(1L);

            var account = new Debit();
            account.setId((long) random.nextInt(1, 15));
            user.setAccount(account);

            bankService.registerUser(user, bank);

            for (int i = 0; i < 60; i++) {
                var tx = new Deposit();
                tx.setId((long) random.nextInt(1, 50));
                tx.setType(org.example.banks.domain.transaction.Type.DEPOSIT);
                tx.setAmount(5);
                tx.setDay(i);
                tx.setAccountFrom(user.getId());
                userService.makeTransaction(tx);
            }
        }


        Assertions.assertEquals(350, userRepository.getById(1L).getAccount().getBalance());
        Assertions.assertEquals(300, userRepository.getById(2L).getAccount().getBalance());
        centralBankService.notifyBanks();
        Assertions.assertEquals(352, (int)userRepository.getById(1L).getAccount().getBalance());
        Assertions.assertEquals(301, (int)userRepository.getById(2L).getAccount().getBalance());
    }
}
