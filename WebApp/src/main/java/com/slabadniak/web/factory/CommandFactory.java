package com.slabadniak.web.factory;
import com.slabadniak.web.command.EmptyCommand;
import com.slabadniak.web.constant.EnumCommand;
import com.slabadniak.web.command.ICommand;
import com.slabadniak.web.exeption.CommandExeption;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandFactory {
    private List<ICommand> commands;

    public CommandFactory(){
        commands = new ArrayList<>();
    }

    public void create(String[] strings) {
        if ((strings == null) || (strings.length == 0)) {
            Collections.addAll(commands,new EmptyCommand());
        } else{
            for (String current:strings) {
                EnumCommand currentEnum = EnumCommand.valueOf(current.toUpperCase());
                commands.add(currentEnum.getCurrentCommand());
            }
        }
    }

    public void execute(HttpServletRequest request) throws CommandExeption {
        for (ICommand command: commands){
            command.execute(request);
        }
    }
}
