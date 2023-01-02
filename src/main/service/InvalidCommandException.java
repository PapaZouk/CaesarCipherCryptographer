package main.service;

import main.util.PrintingUtil;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String command) {
        System.err.printf(PrintingUtil.UNSUPPORTED_CMD, command);
    }
}
