package org.example.banks.commands;

import lombok.AllArgsConstructor;
import org.example.banks.service.CentralBankService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "notify banks"
)
@AllArgsConstructor
public class NotifyBanksCommand  implements Callable<Integer> {

    CentralBankService centralBankService;

    public Integer call() throws Exception {
        centralBankService.notifyBanks();
        System.out.println("banks successfully notified");
        return null;
    }
}
