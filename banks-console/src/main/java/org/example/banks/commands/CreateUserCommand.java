package org.example.banks.commands;


import lombok.AllArgsConstructor;
import org.example.banks.domain.account.Debit;
import org.example.banks.domain.user.User;
import org.example.banks.repository.BankRepository;
import org.example.banks.service.BankService;
import picocli.CommandLine;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "create user"
)
@AllArgsConstructor
public class CreateUserCommand implements Callable<Integer> {

    BankRepository bankRepository;
    BankService bankService;


    @Override
    public Integer call() throws Exception {
        var scanner = new Scanner(System.in);

        var user = new User();

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
        var bankId = scanner.nextLine();
        var bank = bankRepository.getById(UUID.fromString(bankId));

        var account = new Debit();
        user.setAccount(account);

        bankService.registerUser(user, bank);

        System.out.println("user was successfully created");

        return null;
    }

}
