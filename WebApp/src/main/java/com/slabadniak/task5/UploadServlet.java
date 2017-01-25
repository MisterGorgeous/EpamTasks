package com.slabadniak.task5;

import com.slabadniak.task5.command.CommandFactory;
import com.slabadniak.task5.command.ICommand;
import com.slabadniak.task5.exeption.CommandExeption;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final String PATH = "S:/git_rep/Epam/WebApp/src/main/webapp/img/";

    public void init() throws ServletException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        super.destroy();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = null;

        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> files =  sf.parseRequest(request);
            for(FileItem file :files) {
                if(file.getName() != null) { // retrieve icon's owner
                    fileName = file.getName();
                    file.write(new File(PATH + file.getName()));
                }
            }
        } catch (Exception e) {
            throw new ServletException("Command exception ", e);
        }

        request.setAttribute("filename",fileName);

        ICommand com = CommandFactory.create("upload");


        try {
            com.execute(request);
        } catch (CommandExeption e) {
            throw new ServletException("Command exception ", e);
        }


        HttpSession session = request.getSession();
        request.getRequestDispatcher((String)session.getAttribute("currentJSP")).forward(request, response);
    }
}
