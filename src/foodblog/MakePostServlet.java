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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MakePostServlet
 */
@WebServlet("/MakePostServlet")
public class MakePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oid=request.getParameter("oid");
		System.out.println(oid);
		Statement stmt=null;
		Connection currentCon=null;
		ResultSet rs=null;
		try {
		currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery("select orderlist from request where id='"+oid+"'");	
	      rs.next();
	      HttpSession session = request.getSession(true);	   
	      String ol=rs.getString("orderlist");
	      System.out.println(ol);
          session.setAttribute("orderlist",ol);
          response.sendRedirect("makePost.jsp");
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
