package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.AdminDAO;
import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.UsersAssessment;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddActorCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {

        //exeption
        String movie = request.getParameter("movie");
        String year = request.getParameter("movieyear");

        if(movie == null || movie.equals("")){
            //Movie isn't specified
        }

        List<Actor> actors = retrieveActors(request);

        if(actors == null || actors.isEmpty()){
            //Movie isn't specified
        }


        ConnectionPool pool = ConnectionPool.getInstance();
        AdminDAO adminDAO = null;
        UsersAssessment assessment = null;

        try {
            Wrapper connection = pool.getConnection();
            adminDAO = new AdminDAO(connection);

            adminDAO.addActors(movie,year,actors);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // HttpSession session = request.getSession(true);

        CommandFactory.create("cross").execute(request);
    }

    private List<Actor> retrieveActors(HttpServletRequest request){
        String[] fnames = request.getParameterValues("fname");
        String[] sname = request.getParameterValues("sname");
        String[] role = request.getParameterValues("role");
        String[] birthday = request.getParameterValues("birthday");
        String[] birthplace = request.getParameterValues("birthplace");
        List<Actor> actors = new ArrayList<>();

        for(int i=0; i< fnames.length; ++i){
           actors.add(new Actor(fnames[i],sname[i],role[i],birthday[i],birthplace[i]));
        }

        return actors;
    }
}