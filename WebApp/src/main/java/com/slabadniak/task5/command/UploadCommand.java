package com.slabadniak.task5.command;


import com.slabadniak.task5.configuration.ConfigurationManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadCommand implements ICommand {
    // private static final String UPLOAD_DIRECTORY = "S:\\git_rep\\Epam\\WebApp\\src\\main\\webapp\\xml\\";
    @Override
    public void execute(HttpServletRequest request) {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + "upload";
      /*  if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();
                        item.write(new File(appPath + File.separator + name));
                    }
                }
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, e);
            }
        }*/
        HttpSession session = request.getSession(true);
        try {
            Part part = request.getPart("file");
            String fileName = extractFileName(part);
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);

            String fullPath = savePath + File.separator + fileName;
            session.setAttribute("file-path", fullPath);
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.ERROR, e);
        }
         session = request.getSession(true);
        session.setAttribute("currentJSP", ConfigurationManager.getProperty("path.page.start"));

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
