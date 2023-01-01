package main.service;

import java.util.Optional;

public class CommandBuilder {

    public Optional<Command> build(final String line) {
        for (String typeName : Command.Type.valuesNames()) {
            if (line.equalsIgnoreCase(typeName)) {
                Command.Type type = Command.Type.valueOf(typeName);
                return Optional.of(new Command(type));
            }
        }
        return Optional.empty();
    }
}
