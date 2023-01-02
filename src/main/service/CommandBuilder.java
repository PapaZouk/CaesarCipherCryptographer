package main.service;

import java.util.Optional;

public class CommandBuilder {

    public Command build(final String line) throws InvalidCommandException {
        for (Command.Type value : Command.Type.values()) {
            if (line.equals(value.getName())) {
                Command command = new Command();
                command.setType(value);
                return command;
            }
        }
        throw new InvalidCommandException(line);
    }
}
