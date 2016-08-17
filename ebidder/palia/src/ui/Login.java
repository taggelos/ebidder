package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/html/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static Connection conn = ConnectionManager.getInstance().getConnection();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		String username=request.getParameter("username");
	   	String password=request.getParameter("password");
	       
	   	System.out.println("the username is AAAAA:"+username);
	   	System.out.println("the password is:"+password);
	    
	   	HttpSession session = request.getSession();
	   	session.setAttribute("username", username);
	   	session.setAttribute("password", password);

		String sql	=	"select * from users;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql); 
	   	//pstmt.setString(1, get.getEmail());
		//pstmt.setString(2, get.getPassword());
		ResultSet rs = null;
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getString("username"));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("reg.html");
		ConnectionManager.getInstance().close();
		*/
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {


            PreparedStatement pstmt = conn.prepareStatement("Select username,password from users where username=? and password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                if(username.equals("admin")){
                	HttpSession session = request.getSession();
					session.setAttribute("username", username);
					response.sendRedirect("printout.jsp");
                	//response.sendRedirect("admin.html");
                }
                else{
                	
                   // out.println("Correct login credentials");
                }
                	
            } 
            else {
                out.println("Incorrect login credentials");

        		response.sendRedirect("reg.html");
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

}
