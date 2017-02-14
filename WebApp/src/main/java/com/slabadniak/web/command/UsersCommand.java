package com.slabadniak.web.command;

import com.slabadniak.web.entity.User;
import com.slabadniak.web.exeption.CommandExeption;
import com.slabadniak.web.exeption.ServiceExeption;
import com.slabadniak.web.service.UsersService;
import com.slabadniak.web.content.UserContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsersCommand implements ICommand {
    private static final int NUMPAGES = 20;

     @Override
    public void execute(HttpServletRequest request) throws CommandExeption {
        UserContent content;

        try {
            content = UsersService.users();
        } catch (ServiceExeption e) {
            throw new CommandExeption("Service:", e);
        }

        setAttributes(content,request);
        setForwardPage(request);
    }

    private void setAttributes(UserContent content, HttpServletRequest request){
        HttpSession session = request.getSession();

        List<User> users = content.get();
        session.setAttribute("users",users);
        int numPages = (int) Math.ceil(users.size()/NUMPAGES); //number of pages
        session.setAttribute("usersSize",users.size());
        session.setAttribute("userPages",numPages);
        session.setAttribute("currentUserPage",0); //not const.Will be iterated
    }
}
