package ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class pageTwoProcess
 */
@WebServlet("/html/y")
public class pageTwoProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pageTwoProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
	    String surname=request.getParameter("surname");
	    String username=request.getParameter("username");
	    String password=request.getParameter("password");
	    String location=request.getParameter("location");
	    String country=request.getParameter("country");
	    String email=request.getParameter("email");
	    String tax_registration_number=request.getParameter("tax_registration_number");
	    String phone=request.getParameter("phone");
	    
	    System.out.println("The name is:"+name);
	    System.out.println("The surname is:"+surname);
	    System.out.println("The surname is:"+username);
	    System.out.println("The surname is:"+password);
	    System.out.println("The surname is:"+location);
	    System.out.println("The surname is:"+country);
	    System.out.println("The surname is:"+email);
	    System.out.println("The surname is:"+tax_registration_number);
	    System.out.println("The surname is:"+phone);
	    
	    HttpSession ses= request.getSession();
	    ses.setAttribute("name", name);
	    ses.setAttribute("surname", surname);
	    
	    response.sendRedirect("jsp/printout.jsp");

	}

}
