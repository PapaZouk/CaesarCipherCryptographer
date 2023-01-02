package main.service;

import main.CryptographerRunner;
import main.util.PrintingUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cryptanalysis {

    public static void runCryptanalysis() {
        PrintingService.printCryptAnalysisMenu();
        String option = CryptographerRunner.CONSOLE.nextLine();
        if (option.equalsIgnoreCase(PrintingUtil.BRUTE_FORCE_COMMAND)) {
            System.out.println(PrintingUtil.PROVIDE_FILE_NAME);
            String path = CryptographerRunner.CONSOLE.nextLine();
            useBruteForce(Path.of(path));
        } else if (option.equalsIgnoreCase(PrintingUtil.STATISTIC_ANALYSIS)) {
            //TODO
        }
    }

    public static void useBruteForce(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path.toAbsolutePath().toUri()));
             BufferedWriter writer = Files.newBufferedWriter(getOutputPath(path, PrintingUtil.BRUTE_FORCE_FORMAT))) {
            String fileContent = ReadingService.readFileContent(reader);
            System.out.printf("%s[%s]%n", PrintingUtil.READ_CONTENT, path.getFileName());

            AtomicInteger keyCounter = new AtomicInteger(0);
            while (true) {
                String encrypted = Stream.of(fileContent)
                        .map(item -> CaesarCipher.decryptCharacter(item, keyCounter.getAndIncrement()))
                        .map(String::valueOf).collect(Collectors.joining());

                if (encrypted.contains("\s")) {
                    String compared = Stream.of(encrypted)
                            .filter(Cryptanalysis::noSymbols)
                            .filter(Cryptanalysis::containsSyllables)
                            .filter(Cryptanalysis::containsWords)
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

    private static Boolean noSymbols(String item) {
        String[] split = item.split(" ");
        for (int i = 0; i < split.length; i++) {
            for (String symbol : SearchingService.SYMBOLS) {
                if (split[i].contains(symbol)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Boolean containsSyllables(String line) {
        String[] split = line.split(" ");
        for (int i = 0; i < split.length; i++) {
            for (String syllable : SearchingService.SYLLABLES) {
                if (split[i].contains(syllable)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Boolean containsWords(String line) {
        for (String word : SearchingService.WORDS) {
            if (line.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public static void useStaticalAnalysis(Path path, Path comparingPath) {
        try (
                BufferedReader encryptedReader = Files.newBufferedReader(Paths.get(path.toAbsolutePath().toUri()));
                BufferedReader comparatorReader = Files.newBufferedReader(Paths.get(comparingPath.toAbsolutePath().toUri()))
        ) {
            FileStatistics encryptedStatistics = new FileStatistics();
            FileStatistics comparatorStatistics = new FileStatistics();

            String encryptedFileContent = ReadingService.readFileContent(encryptedReader);
            String comparingFileContent = ReadingService.readFileContent(comparatorReader);

            System.out.printf("%s[%s]%n", PrintingUtil.READ_CONTENT, path.getFileName());
            System.out.printf("%s[%s]%n",  PrintingUtil.READ_CONTENT,comparingPath.getFileName());

            TreeMap<String, Long> encryptedLetters = encryptedStatistics.getLettersStatistics(encryptedFileContent);
            encryptedStatistics.collectLettersFrequency(encryptedLetters);
            encryptedStatistics.printFileStatistics();

            TreeMap<String, Long> comparedFileLetters = comparatorStatistics.getLettersStatistics(comparingFileContent);
            comparatorStatistics.collectLettersFrequency(comparedFileLetters);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Path getOutputPath(Path path, String suffix) {
        String fullPath = path.toAbsolutePath().toString();
        String source = fullPath.substring(0, fullPath.lastIndexOf("."));
        String extension = fullPath.substring(fullPath.lastIndexOf("."));
        return Paths.get(source + suffix + extension);
    }

    private enum Type {
        BRUTE_FORCE(PrintingUtil.BRUTE_FORCE_COMMAND),
        STATISTIC_ANALYSIS(PrintingUtil.STATISTIC_ANALYSIS);

        public String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}

