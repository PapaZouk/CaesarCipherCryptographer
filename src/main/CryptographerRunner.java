package main;

import main.service.Command;
import main.service.CommandBuilder;
import main.service.CommandRunner;
import main.service.PrintingService;
import main.util.PrintingUtil;

import java.util.Scanner;

public class CryptographerRunner {

    public static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String[] args) {

        PrintingService.intro();

        while (CONSOLE.hasNext()) {
            String line = CONSOLE.nextLine();

            try {
                CommandBuilder commandBuilder = new CommandBuilder();
                CommandRunner commandRunner = new CommandRunner();
                Command command = commandBuilder.build(line).orElseThrow(NullPointerException::new);
                commandRunner.run(command);

                if (command.getType().getName().equalsIgnoreCase(PrintingUtil.EXIT)) {
                    return;
                }

            } catch (NullPointerException e) {

            }

        }

    }
}

