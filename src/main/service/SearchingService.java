package main.service;

import java.util.List;

public class SearchingService {

    private SearchingService() {
        throw new AssertionError();
    }

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ,.;':\"\n\r!?@#$%^&*()-_[]{}\\|/<>`~" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final List<String> SYLLABLES = List.of(
         "ing", "er", "ter", "al", "ed", "es", "es",
            "tion", "re", "oth", "ry", "de", "ver", "ex", "en",
            "di", "bout", "com", "ple", "con", "per", "un", "der",
            "tle", "ber", "ty", "num", "peo", "ble", "af", "ers",
            "mer", "wa", "ment", "pro", "ar", "ma", "ri", "sen",
            "ture", "fer", "dif", "pa", "tions", "ther", "fore",
            "est", "fa", "la", "ei", "not", "si", "ent", "ven",
            "ev", "ac", "ca", "fol", "ful", "na", "tain", "ning",
            "col", "par", "dis", "ern", "ny", "cit", "po", "cal",
            "mu", "moth", "pic", "im", "coun", "mon", "pe", "lar",
            "por", "fi", "bers", "sec", "ap", "stud", "ad", "tween",
            "gan", "bod", "tence", "ward", "hap", "nev", "ure", "mem",
            "ters", "cov", "ger", "nit", "ran", "dom", "sim", "so",
            "for", "are", "now", "reg", "val", "beau");

    public static final List<String> WORDS = List.of(
            "the", "be", "to", "of", "and", "in", "that", "have", "itfon",
            "he", "youdhis", "from", "we", "her", "or", "will", "one",
            "would", "their", "outiwho", "which", "me", "make",
            "like", "no", "him", "take", "into", "your", "some", "them",
            "other", "then", "look", "come", "over", "also", "after",
            "two", "our", "first", "way", "because", "these", "day",
            "be", "person", "year", "way", "day", "thing", "man", "world",
            "life", "hand", "part", "child", "eye", "woman", "place", "work",
            "week", "government", "number", "probl", "good", "have", "do",
            "say", "get", "make", "know", "take", "see", "come", "think",
            "look", "want", "give", "use", "find", "tell", "ask", "work",
            "seem","feel", "try", "leave", "call", "new", "first",
            "last", "long", "great", "little", "own", "other", "old", "right",
            "big", "high", "different", "small", "large", "next", "early",
            "young", "important", "few", "public", "bad", "same", "able",
            "the", "for", "with", "from", "about", "into", "over", "after", "and", "that",
            "not", "he", "as", "you", "this", "but", "his",
            "they", "her", "she", "will", "one", "all",
            "would", "there", "their");

    public static final List<String> SYMBOLS = List.of(
            "@", "#", "$", "%", "^", "&", "*", "~", "`", "[", "]", "\\", "/");

    public static final List<String> PUNCTUATION_MARKS = List.of(
            ",", ".", "-", "?", "!", " ", "\"", "’",  "“", "”", ":", ";", "\n", "\r"
    );


    public static Boolean noSymbols(String item) {
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

    public static Boolean containsSyllables(String line) {
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

    public static Boolean containsWords(String line) {
        for (String word : SearchingService.WORDS) {
            if (line.contains(word)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isPunctuationMark(String letter) {
        for (String punctuationMark : SearchingService.PUNCTUATION_MARKS) {
            if (letter.equals(punctuationMark)) {
                return false;
            }
        }
        return true;
    }

}
