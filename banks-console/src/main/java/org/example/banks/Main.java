package org.example.banks;

import org.example.banks.commands.*;
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

import picocli.CommandLine;


public class Main {
    public static void main(String[] args) {

        BankRepository bankRepository = new BankRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        BankService bankService = new BankServiceImpl(bankRepository, userService);
        CentralBankService centralBankService = new CentralBankServiceImpl(bankRepository);

        Init.initServices(centralBankService, userService, bankRepository, bankService);

        CommandLine commandLine = new CommandLine(new Commands())
                .addSubcommand(new CreateBankCommand(centralBankService))
                .addSubcommand(new CreateUserCommand(bankRepository, bankService))
                .addSubcommand(new NotifyBanksCommand(centralBankService))
                .addSubcommand(new NotifyUsersCommand(centralBankService))
                .addSubcommand(new CheckBanksCommand(bankRepository))
                .addSubcommand(new CheckUsersCommand(userRepository))
                .addSubcommand(new DepositCommand(userRepository, userService))
                .addSubcommand(new WithdrawalCommand(userRepository, userService))
                .addSubcommand(new TransferCommand(userRepository, userService))
                .addSubcommand(new ExitCommand());

        Scanner scanner = new Scanner(System.in);
        commandLine.setExecutionStrategy(new CommandLine.RunAll());

        while (true) {
            System.out.println("Choose option:");
            System.out.println("• Create bank - 'create bank'");
            System.out.println("• Create user - 'create user'");
            System.out.println("• Notify banks on Charging money - 'notify banks'");
            System.out.println("• Notify users on updates - 'notify users'");
            System.out.println("• Check created banks - 'get banks'");
            System.out.println("• Check created users - 'get users'");
            System.out.println("• Deposit Money - 'deposit'");
            System.out.println("• Withdraw Money - 'withdraw'");
            System.out.println("• Transfer Money - 'transfer'");
            System.out.println("• Exit - 'exit'");
            var string = scanner.nextLine();
            commandLine.execute(string);
        }

    }


}