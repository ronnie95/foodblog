package foodblog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AcceptOrderServlet
 */
@WebServlet("/AcceptOrderServlet")
public class AcceptOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oid=request.getParameter("oid");
		Statement stmt=null;
		Connection currentCon=null;
		
		try {
		currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      int r = stmt.executeUpdate("update request set uvalid='1', rvalid='1' where id='"+oid+"'");	
	      
	      response.sendRedirect("FetchOrdersServlet");
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
