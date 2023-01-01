package main.service;

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
        ENCRYPT("ENCRYPT"),
        DECRYPT("DECRYPT"),
        HELP("HELP"),
        EXIT("EXIT");

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
