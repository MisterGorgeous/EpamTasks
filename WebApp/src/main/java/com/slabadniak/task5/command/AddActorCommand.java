package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.Feedback;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.AddActorService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddActorCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        Feedback feedback = new Feedback();
        boolean done;

        //validation
        String movie = request.getParameter("movie");
        String year = request.getParameter("movieyear");


        List<Actor> actors = retrieveActors(request);

        if (actors == null || actors.isEmpty()) {
            feedback.setMessage("Actors doesn't specified.");
            request.setAttribute(FEEDBACK, feedback);
            return;
        }


        try {
            done =  AddActorService.add(actors, movie, year);
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        if (done) {
            setForwardPage(request);
        } else {
            feedback.setMessage("Such movie haven't found.");
            request.setAttribute(FEEDBACK, feedback);
        }
    }

    private List<Actor> retrieveActors(HttpServletRequest request) {
        String[] fnames = request.getParameterValues("fname");
        String[] sname = request.getParameterValues("sname");
        String[] role = request.getParameterValues("role");
        String[] birthday = request.getParameterValues("birthday");
        String[] birthplace = request.getParameterValues("birthplace");
        List<Actor> actors = new ArrayList<>();

        for (int i = 0; i < fnames.length; ++i) {
            actors.add(new Actor(fnames[i], sname[i], role[i], birthday[i], birthplace[i]));
        }

        return actors;
    }
}