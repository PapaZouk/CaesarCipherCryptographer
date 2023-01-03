package main;

import main.service.*;
import main.util.PrintingUtil;

import java.util.Scanner;

public class CryptographerRunner {

    public static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String[] args) {

        PrintingService.intro();

        while (true) {
            String line = CONSOLE.nextLine();

            if (PrintingUtil.EXIT_COMMAND.equalsIgnoreCase(line)) {
                System.out.println(PrintingUtil.CLOSING);
                return;
            } else if ("".equals(line)) {
                line = CONSOLE.nextLine();
            }
            CommandBuilder commandBuilder = new CommandBuilder();
            CommandRunner commandRunner = new CommandRunner();

            Command command = null;
            try {
                command = commandBuilder.build(line);
                commandRunner.run(command);
            } catch (InvalidCommandException e) {
                PrintingService.printHelpMenu();
            }

        }

    }
}

