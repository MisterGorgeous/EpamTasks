package com.slabadniak.web.command;

import com.slabadniak.web.constant.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOffCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("userStatus", UserType.GUEST);
        session.removeAttribute("user");

        setForwardPage(request);
    }
}
