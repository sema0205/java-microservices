package org.example.banks.commands;

import lombok.AllArgsConstructor;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "exit"
)
@AllArgsConstructor
public class ExitCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("exiting menu...");
        System.exit(0);
        return null;
    }

}
