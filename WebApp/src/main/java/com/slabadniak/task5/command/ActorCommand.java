package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Actor;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.ActorContent;
import com.slabadniak.task5.sessioncontent.DataContext;
import com.slabadniak.task5.sessioncontent.GenreContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class ActorCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) {

        //exeption
        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");

        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        ActorContent content = new ActorContent();


        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.actors(movie.getTitle()));
            //add genres to request atr
            setAtributes(content,request);

            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setAtributes(DataContext content, HttpServletRequest request){
        request.setAttribute("actors", (List<Actor>) content.get());
    }
}
