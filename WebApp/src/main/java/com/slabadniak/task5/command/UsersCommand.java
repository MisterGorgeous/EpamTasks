package com.slabadniak.task5.command;

import com.slabadniak.task5.entity.User;
import com.slabadniak.task5.exeption.CommandExeption;
import com.slabadniak.task5.exeption.ServiceExeption;
import com.slabadniak.task5.service.UsersService;
import com.slabadniak.task5.content.DataContext;
import com.slabadniak.task5.content.UserContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersCommand implements ICommand {
     @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        UsersService service = new UsersService();
        UserContent content;

        try {
            content = service.users();
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAtributes(content,request);
        setForwardPage(request);
    }

    private void setAtributes(DataContext content, HttpServletRequest request){
        //request.setAttribute("users", (List<User>) content.get());
        HttpSession session = request.getSession();

        List<User> users = (List<User>) content.get();
        session.setAttribute("users",users);
        //number of pages
        int numPages = (int) Math.ceil(users.size()/20);
        session.setAttribute("usersSize",numPages);
        //not const.Will be iterated
        session.setAttribute("currentUserPage",0);
    }
}
