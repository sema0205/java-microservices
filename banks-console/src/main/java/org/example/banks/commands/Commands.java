package org.example.banks.commands;

import lombok.AllArgsConstructor;
import picocli.CommandLine;

import java.util.concurrent.Callable;


@AllArgsConstructor
@CommandLine.Command()
public class Commands implements Callable<Integer> {

    public Integer call() {
        return 0;
    }

}
