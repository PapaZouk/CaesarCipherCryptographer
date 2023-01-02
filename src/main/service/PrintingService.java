package main.service;

import main.util.PrintingUtil;

public class PrintingService {
    public static void intro() {
        System.out.printf("%-82s%n%-20s%2s%38s%2s%20s%n%-82s%n",
                "=".repeat(82),
                ">".repeat(15),
                " ", PrintingUtil.WELCOME_TO_CAESAR_CIPHER_CRYPTOGRAPHER,
                " ",
                "<".repeat(15),
                "=".repeat(82));
        printHelpMenu();
    }

    public static void printHelpMenu() {
        System.out.printf("%n%-38s%n%-15s%-10s%-20s%n%-15s%-10s%-20s%n%-15s%-10s%-20s%n%-15s%-10s%-20s%n%-15s%-10s%-20s%n",
                PrintingUtil.CHOOSE_ONE_OF_THE_FOLLOWING_OPTIONS,
                PrintingUtil.ENCRYPT_COMMAND, "->", PrintingUtil.TO_ENCRYPT_FILE,
                PrintingUtil.DECRYPT_COMMAND, "->", PrintingUtil.TO_DECRYPT_FILE,
                PrintingUtil.CRYPTANALYSIS_COMMAND, "->", PrintingUtil.TO_CRYPTANALYSIS,
                PrintingUtil.HELP_COMMAND, "->", PrintingUtil.TO_GET_HELP,
                PrintingUtil.EXIT_COMMAND, "->", PrintingUtil.TO_FINISH_PROGRAM);
    }

    public static void printClosing() {
        System.out.println(PrintingUtil.CLOSING);
    }

    public static void printCryptAnalysisMenu() {
        System.out.printf("%n%-38s%n%-20s%-10s%-30s%n%-20s%-10s%-30s%n",
                PrintingUtil.CHOOSE_ONE_OF_THE_FOLLOWING_OPTIONS,
                PrintingUtil.BRUTE_FORCE_COMMAND, "->", PrintingUtil.TO_BRUTE_FORCE,
                PrintingUtil.STATISTIC_ANALYSIS, "->", PrintingUtil.TO_STATISTIC_ANALYSIS);
    }
}
