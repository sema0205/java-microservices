package org.example.banks.commands;

import lombok.AllArgsConstructor;
import org.example.banks.domain.user.User;
import org.example.banks.repository.UserRepository;
import picocli.CommandLine;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "get users"
)
@AllArgsConstructor
public class CheckUsersCommand implements Callable<Integer> {

    UserRepository userRepository;

    @Override
    public Integer call() throws Exception {
        var usersMap = userRepository.getAll();

        for (Map.Entry<UUID, User> entry : usersMap.entrySet()) {
            var userInfoMap = entry.getValue();

            System.out.printf("user id: %s\n", userInfoMap.getId());
            System.out.printf("user balance: %f\n", userInfoMap.getAccount().getBalance());
            System.out.printf("user name: %s\n", userInfoMap.getName());
            System.out.printf("user status: %s\n\n", userInfoMap.getStatus());

        }

        return null;
    }
}
