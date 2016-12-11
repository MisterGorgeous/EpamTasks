package com.slabadniak.task5.command;

import com.slabadniak.task5.Builder;
import com.slabadniak.task5.configuration.ConfigurationManager;
import com.slabadniak.task5.entity.Journey;
import com.slabadniak.task5.parser.DOMParser;
import com.slabadniak.task5.parser.SAXParser;
import com.slabadniak.task5.parser.StAXParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


public class ParseCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        Builder builder = new Builder(request.getContextPath());
        ArrayList<Journey> results = null;

        String pressedButton = request.getParameter("button");
        if (pressedButton.equals("DOM")) {
            results = builder.create(new DOMParser());
        } else if (pressedButton.equals("SAX")) {
            results = builder.create(new SAXParser());
        } else if (pressedButton.equals("StAX")) {
            results = builder.create(new StAXParser());
        } else {
            results = new ArrayList<>();
        }

        request.setAttribute("results", results);
        HttpSession session = request.getSession(true);
        session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.finish"));
    }
}
