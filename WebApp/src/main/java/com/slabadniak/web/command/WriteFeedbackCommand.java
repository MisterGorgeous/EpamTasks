package com.slabadniak.web.command;

import com.slabadniak.web.configuration.LanguageManager;
import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.exeption.CommandExeption;

import javax.servlet.http.HttpServletRequest;

public class  WriteFeedbackCommand implements ICommand {
    private static final String LOCAL = "local";

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = new Feedback();

        String text = request.getParameter("text");

        String local = (String)request.getSession().getAttribute(LOCAL);

        feedback.setMessage(LanguageManager.getProperty(text,local));
        request.setAttribute(FEEDBACK, feedback);

        setForwardPage(request);
    }
}
