package com.slabadniak.task5.command;

import com.slabadniak.task5.configuration.ConfigurationManager;
import com.slabadniak.task5.dao.ClientDAO;
import com.slabadniak.task5.pool.ConnectionPool;
import com.slabadniak.task5.pool.Wrapper;
import com.slabadniak.task5.sessioncontent.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class QueryCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        ConnectionPool pool = ConnectionPool.getInstance();
        ClientDAO clientDAO = null;
        try {
            Wrapper connection = pool.getConnection();
            clientDAO = new ClientDAO(pool.getConnection());
            setAtributes(clientDAO.findAll(),request);
           pool.closeConnection(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("currentJSP",ConfigurationManager.getProperty("path.page.queryres"));
    }

    private void setAtributes(SessionRequestContent content, HttpServletRequest request){
        request.setAttribute("genres",content.get());
    }
}
