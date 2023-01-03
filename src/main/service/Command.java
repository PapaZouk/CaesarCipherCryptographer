package main.service;

public class Command {

    private Type type;

    public Command() {
        this.type = Type.DEFAULT;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        ENCRYPT("1"),
        DECRYPT("2"),
        CRYPTANALYSIS("3"),
        HELP("4"),
        EXIT("5"),
        DEFAULT("DEFAULT");

        public final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}
