package main.service;

import main.util.PrintingUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CaesarCipher {

    public static void encrypt(Integer key, Path path) {
        try (
                BufferedReader reader = Files.newBufferedReader(Paths.get(path.toAbsolutePath().toUri()));
                BufferedWriter writer = Files.newBufferedWriter(PathService.getOutputPath(path, PrintingUtil.ENCRYPTED_FORMAT))) {
            String fileContent = ReadingService.readFileContent(reader);
            System.out.printf("%s[%s]%n", PrintingUtil.ENCRYPTING_CONTENT, path.getFileName());

            String encrypted = Stream.of(fileContent)
                    .map(item -> encryptCharacter(item, key))
                    .collect(Collectors.joining());

            System.out.printf("%s[%s]%n", PrintingUtil.ENCRYPTING_CONTENT, encrypted);

            writer.write(encrypted);
            writer.flush();
            System.out.printf("%s[%d]%n", PrintingUtil.ENCRYPTION_COMPLETED, key);
            System.out.println(PrintingUtil.EXIT_TO_SEE);
        } catch (IOException e) {
            System.err.println(PrintingUtil.INVALID_PATH_NAME);
        }
    }

    private static String encryptCharacter(String input, Integer key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int pos = SearchingService.ALPHABET.indexOf(input.charAt(i));
            int encryptPos = (pos + key) % 85;
            result.append(SearchingService.ALPHABET.charAt(encryptPos));
        }
        return result.toString();
    }


    public static void decrypt(Integer key, Path path) {
        try (
                BufferedReader reader = Files.newBufferedReader(Paths.get(path.toAbsolutePath().toUri()));
                BufferedWriter writer = Files.newBufferedWriter(PathService.getOutputPath(path, PrintingUtil.DECRYPTED_FORMAT))) {
            String content = ReadingService.readFileContent(reader);

            System.out.printf("%s[%s]%n",
                    PrintingUtil.READING_COMPLETED,
                    path.getFileName());

            String decrypted = Stream.of(content)
                    .map(item -> decryptCharacter(item, key))
                    .map(String::valueOf)
                    .collect(Collectors.joining());

            System.out.printf("%s[%s]%n",
                    PrintingUtil.DECRYPTING_CONTENT,
                    decrypted);

            writer.write(decrypted);
            writer.flush();
            System.out.printf("%s[%d]%n", PrintingUtil.DECRYPTION_COMPLETED, key);
            System.out.println(PrintingUtil.EXIT_TO_SEE);
        } catch (IOException e) {
            System.err.println(PrintingUtil.INVALID_PATH_NAME);
        }
    }

    public static String decryptCharacter(String item, Integer key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < item.length(); i++) {
            int pos = SearchingService.ALPHABET.indexOf(item.charAt(i));
            int decryptPos = (pos - key) % 85;

            if (decryptPos < 0) {
                decryptPos = SearchingService.ALPHABET.length() + decryptPos;
            }
            result.append(SearchingService.ALPHABET.charAt(decryptPos));
        }
        return result.toString();
    }
}
