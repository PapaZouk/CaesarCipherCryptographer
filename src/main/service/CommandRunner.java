package main.service;

import main.CryptographerRunner;
import main.util.PrintingUtil;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class CommandRunner {

    private final Map<Command.Type, Consumer<Command>> EXECUTION_MAP;

    {
        EXECUTION_MAP = Map.of(
                Command.Type.ENCRYPT, this::runEncrypt,
                Command.Type.DECRYPT, this::runDecrypt,
                Command.Type.HELP, this::runHelp,
                Command.Type.EXIT, this::runExit
        );
    }

    public void run(final Command command) {
        try {
            Consumer<Command> commandConsumer = Optional.ofNullable(EXECUTION_MAP.get(command.getType()))
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format(PrintingUtil.UNSUPPORTED_CMD, command.getType())
                    ));
            commandConsumer.accept(command);
        } catch (NullPointerException e) {
            System.out.printf(PrintingUtil.UNSUPPORTED_CMD, command.getType());
        }
    }

    private void runExit(Command command) {
        if (Command.Type.EXIT.getName().equalsIgnoreCase(command.getType().name)) {
            PrintingService.printClosing();
        }
    }

    private void runHelp(Command command) {
        if (Command.Type.HELP.getName().equalsIgnoreCase(command.getType().name)) {
            PrintingService.printHelpMenu();
        }
    }

    private void runEncrypt(final Command command) {
        if (Command.Type.ENCRYPT.getName().equalsIgnoreCase(command.getType().getName())) {
            System.out.println(PrintingUtil.PROVIDE_FILE_NAME);;
            Path input = ReadingService.readFilePath(CryptographerRunner.CONSOLE.nextLine());
            System.out.println(PrintingUtil.KEY_TO_ENCRYPT);
            Integer key = null;
            try {
                key = CryptographerRunner.CONSOLE.nextInt();
            } catch (NumberFormatException e) {
                System.err.printf(PrintingUtil.INVALID_KEY_FORMAT, key);
                key = CryptographerRunner.CONSOLE.nextInt();
            }
            CaesarCipher.encrypt(key, input);
        }
    }

    private void runDecrypt(final Command command) {
        if (Command.Type.DECRYPT.getName().equalsIgnoreCase(command.getType().getName())) {
            System.out.println(PrintingUtil.PROVIDE_FILE_NAME);
            Path input = ReadingService.readFilePath(CryptographerRunner.CONSOLE.nextLine());
            System.out.println(PrintingUtil.KEY_TO_DECRYPT);
            Integer key = null;
            try {
                key = CryptographerRunner.CONSOLE.nextInt();
            } catch (NumberFormatException e) {
                System.err.printf(PrintingUtil.INVALID_KEY_FORMAT, key);
                key = CryptographerRunner.CONSOLE.nextInt();
            }
            CaesarCipher.decrypt(key, input);
        }
    }
}
