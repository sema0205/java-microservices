package org.example.banks;

import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.Interest;
import org.example.banks.domain.bank.Notification;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.transaction.Type;
import org.example.banks.domain.user.Status;
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

import java.util.*;

public class Main {
    public static void main(String[] args) {

        BankRepository bankRepository = new BankRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        BankService bankService = new BankServiceImpl(bankRepository, userRepository);
        UserService userService = new UserServiceImpl(userRepository);
        CentralBankService centralBankService = new CentralBankServiceImpl(bankRepository);

        var random = new Random();

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


        {
            var user = new User();
            user.setId(1L);
            user.setName("lesha");
            user.setLastName("vertov");
            user.setAddress("lenina 23");
            user.setPassportData("1324224");
            var bank = bankRepository.getById(1L);

            var account = new Account();
            account.setId((long) random.nextInt(1, 15));
            account.setType(org.example.banks.domain.account.Type.DEBIT);
            user.setAccount(account);

            bankService.registerUser(user, bank);

            for (int i = 0; i < 70; i++) {
                var tx = new Transaction();
                tx.setId((long) random.nextInt(1, 50));
                tx.setType(Type.DEPOSIT);
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

            var account = new Account();
            account.setId((long) random.nextInt(1, 15));
            account.setType(org.example.banks.domain.account.Type.DEBIT);
            user.setAccount(account);

            bankService.registerUser(user, bank);

            for (int i = 0; i < 60; i++) {
                var tx = new Transaction();
                tx.setId((long) random.nextInt(1, 50));
                tx.setType(Type.DEPOSIT);
                tx.setAmount(5);
                tx.setDay(i);
                tx.setAccountFrom(user.getId());
                userService.makeTransaction(tx);
            }
        }


        while (true) {

            int value;

            System.out.println("Choose option:");
            System.out.println("1. Create bank");
            System.out.println("2. Create user");
            System.out.println("3. Login into user account");
            System.out.println("4. Notify banks on Charging money");
            System.out.println("5. Check created banks");
            System.out.println("6. Check created users");
            System.out.println("7. Exit");

            var scanner = new Scanner(System.in);
            value = scanner.nextInt();
            switch (value) {
                case 1:
                    var bank = new Bank();
                    bank.setId((long) random.nextInt(1, 15));

                    System.out.println("enter bank details:");
                    System.out.print("commission: ");
                    bank.setCommission(scanner.nextDouble());

                    System.out.print("fixed interest: ");
                    bank.setFixedInterest(scanner.nextDouble());

                    System.out.print("credit limit: ");
                    bank.setCreditLimit(scanner.nextDouble());

                    double rate, first, second;
                    var interestList = new ArrayList<Interest>();
                    System.out.println("interest range");

                    System.out.print("rate: ");
                    rate = scanner.nextDouble();
                    System.out.print("first range: ");
                    first = scanner.nextDouble();
                    System.out.print("second range: ");
                    second = scanner.nextDouble();

                    interestList.add(new Interest(rate, first, second));

                    bank.setInterest(interestList);

                    centralBankService.registerBank(bank);
                    System.out.println("bank was successfully created");
                    break;
                case 2:

                    var user = new User();
                    user.setId((long) random.nextInt(1, 15));

                    System.out.println("enter user details:");
                    System.out.print("name: ");
                    user.setName(scanner.next());

                    System.out.print("last name: ");
                    user.setLastName(scanner.next());

                    System.out.print("address: ");
                    user.setAddress(scanner.next());

                    System.out.print("passportData: ");
                    user.setPassportData(scanner.next());

                    System.out.print("bank id: ");
                    var bankId = scanner.nextLong();
                    bank = bankRepository.getById(bankId);

                    var account = new Account();
                    account.setId((long) random.nextInt(1, 15));
                    account.setType(org.example.banks.domain.account.Type.DEBIT);
                    user.setAccount(account);

                    bankService.registerUser(user, bank);

                    System.out.println("user was successfully created");
                    break;
                case 3:

                    System.out.print("enter user id to login: ");

                    var userInfo = userRepository.getById(scanner.nextLong());

                    System.out.printf("user id: %d\n", userInfo.getId());
                    System.out.printf("user balance: %f\n", userInfo.getAccount().getBalance());
                    System.out.printf("user name: %s\n", userInfo.getName());
                    System.out.printf("user status: %s\n", userInfo.getStatus());

                    System.out.println("Choose option:");
                    System.out.println("1. deposit");
                    System.out.println("2. withdrawal");
                    System.out.println("3. transfer");
                    System.out.println("4. Exit");

                    var valueActionUser = scanner.nextInt();

                    var tx = new Transaction();

                    switch (valueActionUser) {
                        case 1:
                            System.out.print("enter money amount: ");
                            var amount1 = scanner.nextLong();

                            tx.setId((long) random.nextInt(1, 25));
                            tx.setType(Type.DEPOSIT);
                            tx.setAmount(amount1);
                            tx.setDay(random.nextInt(1, 25));
                            tx.setAccountFrom(userInfo.getId());

                            var account1 = userService.makeTransaction(tx);

                            System.out.printf("new account balance: %f\n", account1.getBalance());
                            break;
                        case 2:
                            System.out.print("enter money amount: ");
                            var amount2 = scanner.nextLong();

                            tx.setId((long) random.nextInt(1, 25));
                            tx.setType(Type.WITHDRAWAL);
                            tx.setAmount(amount2);
                            tx.setDay(random.nextInt(1, 25));
                            tx.setAccountFrom(userInfo.getId());

                            var account2 = userService.makeTransaction(tx);

                            System.out.printf("new account balance: %f\n", account2.getBalance());
                            break;
                        case 3:
                            System.out.print("enter money amount: ");
                            var amount3 = scanner.nextLong();

                            System.out.print("enter account transfer id to: ");
                            var accountIdTo = scanner.nextLong();

                            tx.setId((long) random.nextInt(1, 25));
                            tx.setType(Type.TRANSFER);
                            tx.setAmount(amount3);
                            tx.setAccountFrom(userInfo.getId());
                            tx.setDay(random.nextInt(1, 25));
                            tx.setAccountTo(accountIdTo);

                            var account3 = userService.makeTransaction(tx);

                            System.out.printf("new account balance: %f\n", account3.getBalance());
                            break;
                        default:
                            break;
                    }


                    break;
                case 4:
                    centralBankService.notifyBanks();
                    System.out.println("banks successfully notified");
                    break;
                case 5:

                    var bankMap = bankRepository.getAll();

                    for (Map.Entry<Long, Bank> entry : bankMap.entrySet()) {
                        var bankInfo = entry.getValue();

                        System.out.printf("bank id : %d\n", bankInfo.getId());
                        System.out.printf("commission: %f\n", bankInfo.getCommission());
                        System.out.printf("fixed interest: %f\n", bankInfo.getFixedInterest());
                        System.out.printf("credit limit: %f\n\n", bankInfo.getCreditLimit());

                    }

                    break;
                case 6:

                    var usersMap = userRepository.getAll();

                    for (Map.Entry<Long, User> entry : usersMap.entrySet()) {
                        var userInfoMap = entry.getValue();

                        System.out.printf("user id: %d\n", userInfoMap.getId());
                        System.out.printf("user balance: %f\n", userInfoMap.getAccount().getBalance());
                        System.out.printf("user name: %s\n", userInfoMap.getName());
                        System.out.printf("user status: %s\n\n", userInfoMap.getStatus());

                    }


                    break;
                case 7:
                    return;
                default:
                    break;
            }

        }


    }
}