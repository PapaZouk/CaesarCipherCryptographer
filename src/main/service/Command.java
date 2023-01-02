package main.service;

import main.util.PrintingUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {

    private final Type type;

    public Command(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        ENCRYPT(PrintingUtil.ENCRYPT_COMMAND),
        DECRYPT(PrintingUtil.DECRYPT_COMMAND),
        CRYPTANALYSIS(PrintingUtil.CRYPTANALYSIS_COMMAND),
        HELP(PrintingUtil.HELP_COMMAND),
        EXIT(PrintingUtil.EXIT_COMMAND),
        DEFAULT("DEFAULT");

        public String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static List<String> valuesNames() {
            return Stream.of(values())
                    .map(Type::getName)
                    .collect(Collectors.toList());
        }
    }
}
