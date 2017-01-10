package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.MovieContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetMainContentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        MovieContent content = new MovieContent();

        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.movies());
            setAtributes(content,request);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //get genre list
        CommandFactory.create("allgenres").execute(request);
        //HttpSession session = request.getSession(true);
       // session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.main"));
        setForwardPage(request);
    }

    private void setAtributes(MovieContent content, HttpServletRequest request){
      // request.setAttribute("films",content.get());
        HttpSession session = request.getSession();
        List<Movie> movies = content.get();
        session.setAttribute("movies",movies);
        //number of pages
        int numPages = (int) Math.ceil(movies.size()/6);
        session.setAttribute("numPages",numPages);
        session.setAttribute("movieSize",movies.size());
        //not const.Will be iterated
        session.setAttribute("currentMoviePage",0);
    }
}
