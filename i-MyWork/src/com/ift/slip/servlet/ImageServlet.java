package com.ift.slip.servlet;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ift.imywork.util.Constant;


@WebServlet("/Images/*")
public class ImageServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getPathInfo();
      
        File file = new File(Constant.IMG_PATH+filename);
        if(!file.exists()){
//        	System.out.println("nopicture");
        	 filename= "Unknown.png";
        	 file = new File(request.getServletContext().getRealPath("/resources/img/"),filename);
        	
        }
        response.setHeader("Content-Type", getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        try{
        	Files.copy(file.toPath(), response.getOutputStream());
        }
        catch(Exception e){
        
        }
    }

}