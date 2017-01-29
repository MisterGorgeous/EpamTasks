package com.slabadniak.web.command;

import com.slabadniak.web.feedback.Feedback;
import com.slabadniak.web.exeption.CommandExeption;

import javax.servlet.http.HttpServletRequest;

public class  WriteFeedbackCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = new Feedback();

        String text = request.getParameter("text");

        feedback.setMessage(text);
        request.setAttribute(FEEDBACK, feedback);

        setForwardPage(request);
    }
}
