package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.exeption.CommandExeption;

import javax.servlet.http.HttpServletRequest;

public class WriteFeedbackCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = new Feedback();

        String text = request.getParameter("text");

        feedback.setMessage(text);
        request.setAttribute(FEEDBACK, feedback);

        setForwardPage(request);
    }

}
