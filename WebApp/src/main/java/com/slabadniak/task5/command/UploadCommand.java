package com.slabadniak.task5.command;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.awt.event.ItemEvent;
import java.io.*;
import java.util.List;
import java.util.logging.Level;

public class UploadCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request) {

    /* ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> files =  sf.parseRequest(request);
            for(FileItem file :files) {
                file.write(new File("S:/git_rep/Epam/WebApp/src/main/webapp/img/" + file.getName()));
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        */

    }
}


