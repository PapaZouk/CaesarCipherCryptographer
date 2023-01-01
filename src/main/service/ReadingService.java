package main.service;

import main.util.PrintingUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadingService {

    public static String readFileContent(BufferedReader reader) throws IOException {
        List<Character> result = new ArrayList<>();
        int characterValue = 0;
        while ((characterValue = reader.read()) != -1) {
            result.add((char) characterValue);
        }
        return result.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));
    }

    public static Path readFilePath(String pathLine) {
        if (pathLine.isEmpty()) {
            throw new InvalidPathException(pathLine, PrintingUtil.EMPTY_PATH);
        }
        try {
            return Path.of(pathLine).toAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidPathException(pathLine, e.getLocalizedMessage());
        }
    }
}
