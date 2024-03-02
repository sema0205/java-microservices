package org.example.banks.repository.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.banks.domain.account.Account;
import org.example.banks.domain.account.Meta;
import org.example.banks.domain.bank.Bank;
import org.example.banks.domain.bank.Interest;
import org.example.banks.domain.transaction.Status;
import org.example.banks.domain.user.User;
import org.example.banks.repository.BankRepository;

import java.util.*;

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
                handleAccount(bank, user.getAccount());
            }

        }
    }

    public HashMap<Long, Bank> getAll() {
        return bankTable;
    }

    private void handleAccount(
            Bank bank,
            Account account
    ) {
        var transactions = account.getTransactions();
        transactions.sort(Comparator.comparingInt(Meta::getDay));

        if (transactions.isEmpty()) {
            return;
        }

        var accountId = account.getId();
        double currentBalance;

        var rates = bank.getInterest();

       switch (account.getType()) {
           case DEBIT:
               var fixedInterest = bank.getFixedInterest() / 365 / 100;
               currentBalance = 0;

               for (Meta meta : transactions) {
                   var tx = meta.getTransaction();

                   if (tx.getStatus().equals(Status.CANCELLED))
                       continue;


                   switch (tx.getType()) {
                       case DEPOSIT:
                           currentBalance += tx.getAmount();
                           break;
                       case WITHDRAWAL:
                           currentBalance -= tx.getAmount();
                           break;
                       case TRANSFER:
                           if (tx.getAccountTo().equals(accountId)) {
                               currentBalance += tx.getAmount();
                           } else {
                               currentBalance -= tx.getAmount();
                           }
                           break;
                   }

                   currentBalance += (currentBalance * fixedInterest);
               }

               account.setBalance(currentBalance);
               break;
           case DEPOSIT:
               double changedInterest;
               currentBalance = 0;

               for (Meta meta : transactions) {
                   var tx = meta.getTransaction();

                   if (tx.getStatus().equals(Status.CANCELLED))
                       continue;

                   switch (tx.getType()) {
                       case DEPOSIT:
                           currentBalance += tx.getAmount();
                           break;
                       case WITHDRAWAL:
                           currentBalance -= tx.getAmount();
                           break;
                       case TRANSFER:
                           if (tx.getAccountTo().equals(accountId)) {
                               currentBalance += tx.getAmount();
                           } else {
                               currentBalance -= tx.getAmount();
                           }
                           break;
                   }

                   changedInterest = handleInterestRates(rates, currentBalance) / 365 / 100;
                   currentBalance += (currentBalance * changedInterest);
               }

               account.setBalance(currentBalance);
               break;
           case CREDIT:
               var commission = bank.getCommission() / 365 / 100;
               currentBalance = bank.getCreditLimit();

               for (Meta meta : transactions) {
                   var tx = meta.getTransaction();

                   if (tx.getStatus().equals(Status.CANCELLED))
                       continue;

                   switch (tx.getType()) {
                       case DEPOSIT:
                           currentBalance += tx.getAmount();
                           break;
                       case WITHDRAWAL:
                           currentBalance -= tx.getAmount();
                           break;
                       case TRANSFER:
                           if (tx.getAccountTo().equals(accountId)) {
                               currentBalance += tx.getAmount();
                           } else {
                               currentBalance -= tx.getAmount();
                           }
                           break;
                   }

                   if (currentBalance < 0) {
                       currentBalance -= (currentBalance * commission);
                   }
               }

               account.setBalance(currentBalance);
               break;
       }

    }


    private double handleInterestRates(
            List<Interest> rates,
            double amount
    ) {
        for (Interest interest : rates) {
            if (amount >= interest.getAmountFrom() && amount <= interest.getAmountUpTo()) {
                return interest.getRate();
            }
        }

        return rates.getFirst().getRate();
    }
}
