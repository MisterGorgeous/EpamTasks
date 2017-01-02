package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.entity.Movie;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.DataContext;
import com.slabadniak.task5.sessioncontent.GenreContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class AllGenresCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {

        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        GenreContent content = new GenreContent();


        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            content.insert(defaultDAO.allGenres());
            //add genres to request atr
            setAtributes(content,request);

            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CommandFactory.create("cross").execute(request);

    }

    private void setAtributes(DataContext content, HttpServletRequest request){
        //request.setAttribute("allgenres", (Set<String>) content.get());
        HttpSession session = request.getSession();
        session.setAttribute("genrelist",(List<String>) content.get());
    }
}
