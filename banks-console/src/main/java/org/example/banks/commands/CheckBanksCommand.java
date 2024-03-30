package org.example.banks.commands;

import lombok.AllArgsConstructor;
import org.example.banks.domain.bank.Bank;
import org.example.banks.repository.BankRepository;
import picocli.CommandLine;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "get banks"
)
@AllArgsConstructor
public class CheckBanksCommand implements Callable<Integer> {

    BankRepository bankRepository;


    public Integer call() throws Exception {
        var bankMap = bankRepository.getAll();

        for (Map.Entry<UUID, Bank> entry : bankMap.entrySet()) {
            var bankInfo = entry.getValue();

            System.out.printf("bank id : %s\n", bankInfo.getId());
            System.out.printf("commission: %f\n", bankInfo.getCommission());
            System.out.printf("fixed interest: %f\n", bankInfo.getFixedInterest());
            System.out.printf("credit limit: %f\n\n", bankInfo.getCreditLimit());

        }
        return null;
    }

}
