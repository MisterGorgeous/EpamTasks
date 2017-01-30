package com.slabadniak.web.factory;
import com.slabadniak.web.command.EmptyCommand;
import com.slabadniak.web.constant.EnumCommand;
import com.slabadniak.web.command.ICommand;

public class CommandFactory {

    private CommandFactory() {
    }

    public static ICommand create(String type) {
        ICommand current;
        if (type == null || type.isEmpty()) {
           current = new EmptyCommand();;
        } else{
			EnumCommand currentEnum = EnumCommand.valueOf(type.toUpperCase());
			current = currentEnum.getCurrentCommand();
		}
        return current;
    }
}
