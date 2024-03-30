package org.example.banks.commands;

import lombok.AllArgsConstructor;
import org.example.banks.service.CentralBankService;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "notify users"
)
@AllArgsConstructor
public class NotifyUsersCommand  implements Callable<Integer> {

    CentralBankService centralBankService;

    public Integer call() throws Exception {
        centralBankService.notifyUsers();
        System.out.println("users successfully notified");
        return null;
    }

}