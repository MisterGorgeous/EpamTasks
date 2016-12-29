package com.slabadniak.task5.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    Logger LOGGER = LogManager.getLogger(ICommand.class);
    void execute(HttpServletRequest request);
}
