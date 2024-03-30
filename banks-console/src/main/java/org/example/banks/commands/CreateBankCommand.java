package org.example.banks.commands;


import lombok.AllArgsConstructor;
import org.example.banks.domain.bank.BankImpl;
import org.example.banks.domain.bank.Interest;
import org.example.banks.service.CentralBankService;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;

@AllArgsConstructor
@CommandLine.Command(
        name = "create bank"
)
public class CreateBankCommand implements Callable<Integer> {

    CentralBankService centralBankService;

    @Override
    public Integer call() throws Exception {
        var scanner = new Scanner(System.in);

        var bank = new BankImpl();

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

        return null;
    }
}