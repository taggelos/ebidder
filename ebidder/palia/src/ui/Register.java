package ui;

import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
  
@WebServlet("/html/re")
public class Register extends HttpServlet {  
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection conn = ConnectionManager.getInstance().getConnection();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		          
		String name=request.getParameter("name");
	    String surname=request.getParameter("surname");
	    String username=request.getParameter("username");
	    String password=request.getParameter("password");
	    String location=request.getParameter("location");
	    String country=request.getParameter("country");
	    String email=request.getParameter("email");
	    String tax_registration_number=request.getParameter("tax_registration_number");
	    String phone=request.getParameter("phone");
	    
	    String sql	=	"insert into users (name,surname,username,password,location,country,email,tax_registration_number,phone) values(?,?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt = null;    
	    try {
			pstmt = conn.prepareStatement(sql);
		
	    
	    /*
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  56
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","system","oracle");  
		  
		PreparedStatement ps=con.prepareStatement(  
		"insert into users  values(?,?,?,?,?,?,?,?,?)");  
		  */
			
	    pstmt.setString(1,name);  
	    pstmt.setString(2,surname);
	    pstmt.setString(3,username);  
	    pstmt.setString(4,password);  
		pstmt.setString(5,location); 
		pstmt.setString(6,country); 
		pstmt.setString(7,email); 
		pstmt.setString(8,tax_registration_number); 
		pstmt.setString(9,phone); 
		
		/*User user = new User();
	    user.setName(name);
	    user.setSurname(surname);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setLocation(location);
	    user.setCountry(country);
	    user.setEmail(email);
	    user.setTaxRegistrationNumber(tax_registration_number);
	    user.setPhone(phone);*/
		pstmt.executeUpdate();
		
		out.print("You are successfully registered...");
	    }  
	    catch (SQLException e) {
			// TODO Auto-generated catch block
	    	out.print("BITCH...");	
			e.printStackTrace();
		}
	        
	    
	}	
	/*
	private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }*/
}  