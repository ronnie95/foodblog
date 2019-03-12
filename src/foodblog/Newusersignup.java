package foodblog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Newusersignup
 */
@WebServlet("/Newusersignup")
public class Newusersignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 static Connection currentCon = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Newusersignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Statement stmt = null;   
		
		String a=request.getParameter("fname");
		String b=request.getParameter("lname");
		String c=request.getParameter("un");
		String d=request.getParameter("pw");
		
		
		
	      try {
			
			 currentCon = ConnectionManager.getConnection();
			 stmt=currentCon.createStatement();
			 ResultSet rs=null;
			 String cquery="select username from users";
			 rs=stmt.executeQuery(cquery);
			 
			 while(rs.next())
			 {
				 if(c.equals(rs.getString("username")))
				 {
					 response.sendRedirect("duplicateuser.jsp");
				 }
			 }
			 
			 String query="insert into users (username,password,FirstName,LastName,rewards) values ('"+c+"','"+d+"','"+a+"','"+b+"','0')";
			 stmt.executeUpdate(query);
			 System.out.println("User Inserted");
				 response.sendRedirect("login.jsp");
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
