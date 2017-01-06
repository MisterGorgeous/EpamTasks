package com.slabadniak.task5.command;

import com.slabadniak.task5.dao.DefaultDAO;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.MovieContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetMainContentCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        ConnectionPool pool = ConnectionPool.getInstance();
        DefaultDAO defaultDAO = null;
        try {
            Wrapper connection = pool.getConnection();
            defaultDAO = new DefaultDAO(connection);
            setAtributes(defaultDAO.movies(),request);
            pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //HttpSession session = request.getSession(true);
       // session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.main"));
        setForwardPage(request);
    }

    private void setAtributes(MovieContent content, HttpServletRequest request){
      // request.setAttribute("films",content.get());
        HttpSession session = request.getSession();
        session.setAttribute("films",content.get());
    }
}
