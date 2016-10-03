package ui;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BidDAO;
import db.ImageDAO;
import entities.Photo;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.BufferedInputStream;

@ApplicationScoped
@WebServlet("/images/*")
public class ImageRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{imageDAO}")
	private ImageDAO imageDAO;
	
	
    public ImageDAO getImageDAO() {
		return imageDAO;
	}


	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
    	
    	//ImageDAO imageDAO = (ImageDAO) getServletContext().getAttribute("imageDAO"); 
    	
        String imageId = String.valueOf(request.getPathInfo().substring(1)); 
        Photo image= imageDAO.findImage(Integer.valueOf(imageId) );
        
        
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
        	InputStream image_data = new ByteArrayInputStream(image.getPhoto());
            input = new BufferedInputStream(image_data); 
            output = new BufferedOutputStream(response.getOutputStream());
            byte[] buffer = new byte[16777215];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
        } finally {
            if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
            if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
        }
        
    }    
}



