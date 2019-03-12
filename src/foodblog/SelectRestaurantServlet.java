package foodblog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectRestaurantServlet
 */
@WebServlet("/SelectRestaurantServlet")
public class SelectRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRestaurantServlet() {
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
		String rid=request.getParameter("res");
		try {
		currentCon = ConnectionManager.getConnection();
	      stmt=currentCon.createStatement();
	      rs = stmt.executeQuery("select * from menu where rid='"+rid+"'");
	      ArrayList<MenuBean> MB=new ArrayList<MenuBean>();
	      while(rs.next())
	      {
	    	  MenuBean M=new MenuBean();
	    	  M.setName(rs.getString("name"));
	    	  M.setPrice(rs.getDouble("price"));
	    	  MB.add(M);
	      }
	      HttpSession session = request.getSession(true);	    
          session.setAttribute("menu",MB);
          session.setAttribute("rid",rid );
         
          response.sendRedirect("order.jsp"); 
	      
	}
	catch(Exception E)
		{
			
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
