package main.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStatistics {
    private TreeMap<String, Long> fileStatistics = null;

    public FileStatistics() {
        fileStatistics = new TreeMap<>();
    }

    public void printFileStatistics() {
        fileStatistics.forEach((k, v) -> System.out.println("Letter: " + k + ", frequency: " + v));
    }

    public Map<String, Long> getFileStatistics() {
        return fileStatistics;
    }

    public TreeMap<String, Long> getLettersStatistics(String fileContent) {
        return Stream.of(fileContent)
                .map(line -> line.split(""))
                .flatMap(Stream::of)
                .filter(FileStatistics::isPunctuationMark)
                .collect(Collectors.groupingBy(
                        letter -> letter,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

    public void collectLettersFrequency(TreeMap<String, Long> lettersStatisticMap) {
        int size = lettersStatisticMap.size();
        List<Long> frequency = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            frequency.add(getNumberOfOccurrence(lettersStatisticMap, i));
        }

        for (Map.Entry<String, Long> entry : lettersStatisticMap.entrySet()) {
            for (Long num : frequency) {
                if (num.equals(entry.getValue())) {
                    fileStatistics.put(entry.getKey(), num);
                    break;
                }
            }
        }
    }

    private static boolean isPunctuationMark(String letter) {
        for (String punctuationMark : SearchingService.PUNCTUATION_MARKS) {
            if (letter.equals(punctuationMark)) {
                return false;
            }
        }
        return true;
    }
    private static Long getNumberOfOccurrence(TreeMap<String, Long> lettersStatisticMap, Integer position) {
        return Stream.of(lettersStatisticMap)
                .map(TreeMap::values)
                .flatMap(Collection::stream)
                .sorted(Comparator.reverseOrder())
                .skip(position)
                .findFirst()
                .get();
    }


}
