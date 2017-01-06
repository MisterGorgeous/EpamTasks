package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class EmptyCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        LOGGER.log(Level.INFO, "Empty Command");
    }
}
