package foodblog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;

/**
 * Servlet implementation class AllRestaurantsServlet
 */
@WebServlet("/AllRestaurantsServlet")
public class AllRestaurantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllRestaurantsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Statement stmt=null;
		Connection currentCon=null;
		ResultSet rs=null;
		try {
		currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery("select name from restaurant");	
	      ArrayList<String> S=new ArrayList<String>();
	      while(rs.next())
	    	  S.add(rs.getString("name"));
	      HttpSession session = request.getSession(true);	    
	      session.setAttribute("allRestaurants",S);
	      response.sendRedirect("order.jsp");
	      
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage());
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
