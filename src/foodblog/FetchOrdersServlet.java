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
 * Servlet implementation class FetchOrdersServlet
 */
@WebServlet("/FetchOrdersServlet")
public class FetchOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 ArrayList<RequestOrder> R=new ArrayList<RequestOrder>();
		   Statement stmt = null;   
		    
			
		   try 
		   {
		      //connect to DB 
			   Connection currentCon;
		      currentCon = ConnectionManager.getConnection();
		      stmt=currentCon.createStatement();
		      
		      
		      String rid= ((RestaurantBean)request.getSession(false).getAttribute("currentRestaurant")).getResName();
		      ResultSet postquery = stmt.executeQuery("select * from request where rid='"+rid+"'");
		      while(postquery.next())
		      {
		    	  RequestOrder temp=new RequestOrder();
		    	  temp.setId(postquery.getInt("id"));
		    	  temp.setOrderList(postquery.getString("orderlist"));
		    	  temp.setRid(postquery.getString("rid"));
		    	  temp.setUid(postquery.getString("uid"));
		    	  temp.setRvalid(postquery.getInt("rvalid"));
		    	  temp.setUvalid(postquery.getInt("uvalid"));
		    	  R.add(temp);
		    	  
		      }
		      
		      HttpSession session = request.getSession(true);	    
	          session.setAttribute("pending",R);
	          response.sendRedirect("showPending.jsp"); 
		      
		      //boolean more = postquery.next();
			 
		   } 

		   catch (Exception ex) 
		   {
		      System.out.println(ex.getMessage());
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
