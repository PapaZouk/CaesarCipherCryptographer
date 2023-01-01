package main.service;

import main.util.PrintingUtil;

import java.util.stream.Stream;

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
        System.out.printf("%-38s%n%-10s%-10s%-20s%n%-10s%-10s%-20s%n%-10s%-10s%-20s%n%-10s%-10s%-20s%n",
                PrintingUtil.CHOOSE_ONE_OF_THE_FOLLOWING_COMMAND,
                "[1]", "->", PrintingUtil.TO_ENCRYPT_FILE,
                "[2]", "->", PrintingUtil.TO_DECRYPT_FILE,
                "[HELP]", "->", PrintingUtil.TO_GET_HELP,
                "[EXIT]", "->", PrintingUtil.TO_FINISH_PROGRAM);
    }

    public static void printClosing() {
        System.out.println(PrintingUtil.CLOSING);
    }
}
