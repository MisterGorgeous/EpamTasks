package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.DataContext;
import com.slabadniak.task5.sessioncontent.GenreContent;
import com.slabadniak.task5.sessioncontent.MovieContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

public class GenreCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest request) {
        //exeption

        HttpSession session = request.getSession();
        Movie movie = (Movie) session.getAttribute("chosenMovie");

        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        GenreContent content = new GenreContent();


        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.genres(movie.getTitle()));
            //add genres to request atr
            setAtributes(content,request);

            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setAtributes(DataContext content, HttpServletRequest request){
         request.setAttribute("genres", (List<String>) content.get());
    }
}
