package org.example.banks.commands;

import lombok.AllArgsConstructor;
import org.example.banks.domain.transaction.Transaction;
import org.example.banks.domain.transaction.Type;
import org.example.banks.repository.UserRepository;
import org.example.banks.service.UserService;
import picocli.CommandLine;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;


@CommandLine.Command(
        name = "transfer"
)
@AllArgsConstructor
public class TransferCommand implements Callable<Integer> {

    UserRepository userRepository;
    UserService userService;

    public Integer call() throws Exception {
        var random = new Random();
        var scanner = new Scanner(System.in);
        System.out.print("enter user id to login: ");

        var userInfo = userRepository.getById(scanner.nextLong());

        System.out.printf("user id: %d\n", userInfo.getId());
        System.out.printf("user balance: %f\n", userInfo.getAccount().getBalance());
        System.out.printf("user name: %s\n", userInfo.getName());
        System.out.printf("user status: %s\n", userInfo.getStatus());

        var tx = new Transaction();

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

        var account3 = userService.makeTransferTransaction(tx);

        System.out.printf("new account balance: %f\n", account3.getBalance());

        return null;
    }
}