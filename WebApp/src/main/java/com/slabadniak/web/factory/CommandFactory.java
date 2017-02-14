package com.slabadniak.web.factory;
import com.slabadniak.web.command.EmptyCommand;
import com.slabadniak.web.constant.EnumCommand;
import com.slabadniak.web.command.ICommand;
import com.slabadniak.web.exeption.CommandExeption;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Create and execute commands
 */
public class CommandFactory {
    private List<ICommand> commands;

    public CommandFactory(){
        commands = new LinkedList<>();
    }

    /**
     * Take command's array, construct list of the commands or just
     * empty command.
     * @param strings
     */
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

    /**
     * Execute command, simulate behavior of the queue.
     * @param request
     * @throws CommandExeption
     */
    public void execute(HttpServletRequest request) throws CommandExeption {
        for (ICommand command: commands){
            command.execute(request);
        }
    }
}
