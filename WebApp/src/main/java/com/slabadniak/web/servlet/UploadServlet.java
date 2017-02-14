package com.slabadniak.web.servlet;

import com.slabadniak.web.factory.CommandFactory;
import com.slabadniak.web.util.CheckContentType;
import org.apache.commons.fileupload.FileItem;
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

/**
 * This class is a servlet class used to dowloand apllication artifacts.
 * @see HttpServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final String JSP = "currentJSP";
    private static final String FILENAME = "filename";

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
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> files = sf.parseRequest(request);
            for (FileItem file : files) {
                if (file.getName() != null) { // retrieve icon's owner
                    if (CheckContentType.isValid(file.getContentType())) {
                        file.write(new File(getServletContext().getRealPath("/img") + '/' + file.getName()));
                        request.setAttribute(FILENAME, file.getName());
                        //load file on server
                        CommandFactory factory = new CommandFactory();
                        factory.create(new String[]{"upload"});
                        factory.execute(request);
                    }
                }
            }
        } catch (Exception e) {
            throw new ServletException("Command exception ", e);
        }

        HttpSession session = request.getSession();
        request.getRequestDispatcher((String) session.getAttribute(JSP)).forward(request, response);
    }
}
