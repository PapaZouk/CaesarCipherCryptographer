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
                Command.Type.CRYPTANALYSIS, this::runCryptAnalysis,
                Command.Type.HELP, this::runHelp,
                Command.Type.DEFAULT, this::runExit,
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
        } catch (Exception e) {
            System.err.printf(PrintingUtil.UNSUPPORTED_CMD);
        }
    }

    private void runExit(Command command) {
        PrintingService.printClosing();
        System.exit(0);
    }

    private void runHelp(Command command) {
        PrintingService.printHelpMenu();
    }

    private void runEncrypt(final Command command) {
        System.out.println(PrintingUtil.PROVIDE_FILE_NAME);
        Path input =null;
        try {
            input = ReadingService.readFilePath(CryptographerRunner.CONSOLE.nextLine());
        } catch (NumberFormatException e) {
            System.err.println(PrintingUtil.INVALID_PATH_NAME);
            return;
        }

        System.out.println(PrintingUtil.KEY_TO_ENCRYPT);
        Integer key = null;
        try {
            key = CryptographerRunner.CONSOLE.nextInt();
        } catch (NumberFormatException e) {
            System.err.printf(PrintingUtil.INVALID_KEY_FORMAT, key);
            return;
        }
        CaesarCipher.encrypt(key, input);
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

    private void runCryptAnalysis(Command command) {
        Cryptanalysis.runCryptanalysis();
    }
}
