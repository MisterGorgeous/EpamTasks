package com.slabadniak.task5.command;


public class CommandFactory {

    private CommandFactory() {
    }

    public static ICommand create(String type) {
        ICommand current = new EmptyCommand();

        if (type == null || type.isEmpty()) {
            return current;
        }

        //no such command exeption
        EnumCommand currentEnum = EnumCommand.valueOf(type.toUpperCase());
        current = currentEnum.getCurrentCommand();

        return current;

    }
}
