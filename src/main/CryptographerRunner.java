package main;

import main.service.Command;
import main.service.CommandBuilder;
import main.service.CommandRunner;
import main.service.PrintingService;
import main.util.PrintingUtil;

import java.util.Optional;
import java.util.Scanner;

public class CryptographerRunner {

    public static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String[] args) {

        PrintingService.intro();

        while (CONSOLE.hasNext()) {
            String line = CONSOLE.nextLine();

            if (PrintingUtil.EXIT_COMMAND.equalsIgnoreCase(line)) {
                return;
            }
            CommandBuilder commandBuilder = new CommandBuilder();
            CommandRunner commandRunner = new CommandRunner();

            Optional<Command> command = commandBuilder.build(line);
            commandRunner.run(command.orElse(new Command(Command.Type.DEFAULT)));

        }

    }
}

