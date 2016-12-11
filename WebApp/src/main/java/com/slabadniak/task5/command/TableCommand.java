package com.slabadniak.task5.command;

import com.slabadniak.task5.tag.VendorMap;
import com.slabadniak.task5.configuration.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TableCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
        VendorMap map = new VendorMap();
        request.setAttribute("rw", map);

        HttpSession session = request.getSession(true);
        session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.table"));

    }
}
