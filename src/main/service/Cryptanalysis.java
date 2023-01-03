package main.service;

import main.CryptographerRunner;
import main.util.PrintingUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cryptanalysis {

    public static void runCryptanalysis() {
        PrintingService.printCryptAnalysisMenu();
        String option = CryptographerRunner.CONSOLE.nextLine();
        if (option.equalsIgnoreCase(PrintingUtil.BRUTE_FORCE_COMMAND)) {
            System.out.println(PrintingUtil.PROVIDE_ENCRYPTED_FILE);
            String input = CryptographerRunner.CONSOLE.nextLine();

            useBruteForce(Path.of(input));
        } else if (option.equalsIgnoreCase(PrintingUtil.STATISTIC_ANALYSIS)) {
            System.out.println(PrintingUtil.PROVIDE_ENCRYPTED_FILE);
            String input = CryptographerRunner.CONSOLE.nextLine();

            System.out.println(PrintingUtil.PROVIDE_FILE_TO_COMPARE);
            String compareInput = CryptographerRunner.CONSOLE.nextLine();

            useStaticalAnalysis(Path.of(input), Path.of(compareInput));
        }
    }

    public static void useBruteForce(Path path) {
        try (
                BufferedReader reader = Files.newBufferedReader(
                        Paths.get(path.toAbsolutePath().toUri()));
                BufferedWriter writer = Files.newBufferedWriter(
                        PathService.getOutputPath(path, PrintingUtil.BRUTE_FORCE_FORMAT))) {

            String fileContent = ReadingService.readFileContent(reader);
            System.out.printf("%s[%s]%n", PrintingUtil.READING_COMPLETED, path.getFileName());

            AtomicInteger keyCounter = new AtomicInteger(0);
            while (true) {
                String encrypted = Stream.of(fileContent)
                        .map(item -> CaesarCipher.decryptCharacter(item, keyCounter.getAndIncrement()))
                        .map(String::valueOf).collect(Collectors.joining());

                if (encrypted.contains("\s")) {
                    String compared = Stream.of(encrypted)
                            .filter(SearchingService::noSymbols)
                            .filter(SearchingService::containsSyllables)
                            .filter(SearchingService::containsWords)
                            .collect(Collectors.joining());
                    if (!compared.isEmpty()) {
                        System.out.printf("%s[%s]%n",
                                PrintingUtil.KEY_USED_TO_BRUTE_FORCE,
                                keyCounter.getAndDecrement() - 1);
                        writer.write(compared);
                        writer.flush();
                        System.out.println(PrintingUtil.EXIT_TO_SEE);
                        return;
                    }
                    keyCounter.incrementAndGet();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void useStaticalAnalysis(Path path, Path comparingPath) {
        try (
                BufferedReader encryptedReader = Files.newBufferedReader(Paths.get(path.toAbsolutePath().toUri()));
                BufferedReader comparatorReader = Files.newBufferedReader(Paths.get(comparingPath.toAbsolutePath().toUri()))
        ) {
            String encryptedFileContent = ReadingService.readFileContent(encryptedReader);
            String comparingFileContent = ReadingService.readFileContent(comparatorReader);
            System.out.printf("%s[%s]%n", PrintingUtil.READING_COMPLETED, path.getFileName());
            System.out.printf("%s[%s]%n",  PrintingUtil.READING_COMPLETED,comparingPath.getFileName());

            Integer keyValue = searchForDecryptionKey(encryptedFileContent, comparingFileContent);
            System.out.printf(PrintingUtil.FOUND_KEY_FOR_DECRYPTION, keyValue);

            if (keyValue != null) {
                CaesarCipher.decrypt(Math.abs(keyValue), path);
            } else {
            System.err.println(PrintingUtil.SEARCHING_FAILED);
            }

        } catch (IOException e) {
            System.err.println(PrintingUtil.INVALID_PATH_NAME);
        }
    }

    private static Integer searchForDecryptionKey(String encryptedContent, String comparedContent) {
        FileStatistics encryptedStatistics = new FileStatistics();
        FileStatistics comparatorStatistics = new FileStatistics();

        encryptedStatistics.collectLettersFrequency(encryptedContent);
        comparatorStatistics.collectLettersFrequency(comparedContent);

        return encryptedStatistics.getDecryptionKey(comparatorStatistics);
    }

}

