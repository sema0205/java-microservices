package org.example.banks;

import org.example.banks.domain.account.Debit;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.Interest;
import org.example.banks.domain.transaction.Deposit;
import org.example.banks.domain.transaction.Type;
import org.example.banks.domain.user.User;
import org.example.banks.repository.BankRepository;
import org.example.banks.service.BankService;
import org.example.banks.service.CentralBankService;
import org.example.banks.service.UserService;

import java.util.ArrayList;
import java.util.Random;

public class Init {
    public static void initServices(
        CentralBankService centralBankService,
        UserService userService,
        BankRepository bankRepository,
        BankService bankService
    ) {
        var defaultBank = new Bank();
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

        var random = new Random();

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
                tx.setType(Type.DEPOSIT);
                tx.setAmount(5);
                tx.setDay(i);
                tx.setAccountFrom(user.getId());
                userService.makeTransaction(tx);
            }
        }
    }
}
