package com.slabadniak.task5.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.util.logging.Level;

public class UploadCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {
/*
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + "upload";
        HttpSession session = request.getSession(true);

        try {
            Part part = request.getPart("file");
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);

            String fullPath = savePath + File.separator + fileName;
            session.setAttribute("file-path", fullPath);
        } catch (IOException | ServletException exception) {
            LOGGER.error(exception);
        }

        // return ConfigurationManager.getProperty("path.page.index");*/
    }

  /*  private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }*/
}


