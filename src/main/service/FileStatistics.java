package main.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStatistics {
    private TreeMap<String, Long> fileStatisticsMap = null;

    public FileStatistics() {
        fileStatisticsMap = new TreeMap<>();
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

    public void collectLettersFrequency(String fileContent) {
        TreeMap<String, Long> lettersStatisticMap = getLettersStatistics(fileContent);
        int size = lettersStatisticMap.size();
        List<Long> frequency = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            frequency.add(getNumberOfOccurrence(lettersStatisticMap, i));
        }

        for (Map.Entry<String, Long> entry : lettersStatisticMap.entrySet()) {
            for (Long num : frequency) {
                if (num.equals(entry.getValue())) {
                    if (!entry.getKey().equals("\n")) {
                        fileStatisticsMap.put(entry.getKey(), num);
                    }
                    break;
                }
            }
        }
    }

    public Integer getDecryptionKey(FileStatistics comparatorStatistics) {
        String encryptedMostFrequentLetter = this.getMostFrequentLetter();
        String comparedMostFrequentLetter = comparatorStatistics.getMostFrequentLetter();

        int comparedLetterPosition = SearchingService.ALPHABET.indexOf(comparedMostFrequentLetter);
        int encryptedLetterPosition = SearchingService.ALPHABET.indexOf(encryptedMostFrequentLetter);

        return encryptedLetterPosition - comparedLetterPosition;
    }

    private TreeMap<String, Long> getLettersStatistics(String fileContent) {
        return Stream.of(fileContent)
                .map(line -> line.split(""))
                .flatMap(Stream::of)
                .filter(SearchingService::isPunctuationMark)
                .collect(Collectors.groupingBy(
                        letter -> letter,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

    private String getMostFrequentLetter() {
        return fileStatisticsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .findFirst()
                .map(Map.Entry::getKey)
                .get();
    }
}
