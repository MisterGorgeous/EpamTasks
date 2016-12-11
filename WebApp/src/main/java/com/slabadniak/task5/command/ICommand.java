package com.slabadniak.task5.command;

import com.slabadniak.task5.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    static final Logger LOGGER = LogManager.getLogger(Builder.class);
    void execute(HttpServletRequest request);
}
