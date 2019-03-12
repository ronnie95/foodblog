package foodblog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ContactRestaurantServlet
 */
@WebServlet("/ContactRestaurantServlet")
public class ContactRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactRestaurantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In contactrestaurantservlet");
		int check[]=new int[200];
		int c=0;
		while (request.getParameterMap().containsKey("item"+c)) {
            check[c] = Integer.parseInt(request.getParameter("item"+c));
            System.out.println("item"+c);
            c++;
        }
		ArrayList<MenuBean> MB=(ArrayList<MenuBean>) request.getSession(false).getAttribute("menu");
		if(MB==null) MB=new ArrayList<MenuBean>();
		String rid=(String)request.getSession(false).getAttribute("rid");
		String uid=((UserBean)request.getSession(false).getAttribute("currentSessionUser")).getUsername();
		String req="";
		System.out.println(rid+" "+uid);
		double price=0;
		 c=0;
		for(MenuBean obj : MB)
		{
			if(check[c]!=0)
			{
				price+=obj.getPrice()*check[c];
				req+=obj.getName()+"x"+check[c]+" | ";
			}
			c++;
		}
		req+="Price :"+price;
		RequestOrder R=new RequestOrder();
		R.setOrderList(req);
		R.setUid(uid);
		R.setRid(rid);
		R.setUvalid(0);
		R.setRvalid(0);
		OrderDAO.setOrder(R);
		
		ArrayList<RequestOrder> RQ=(ArrayList<RequestOrder>) request.getSession(false).getAttribute("orders");
		if(RQ==null) RQ=new ArrayList<RequestOrder>();
		RQ.add(R);
		HttpSession session = request.getSession(true);	    
        session.setAttribute("orders",RQ);
        
        response.sendRedirect("showOrders.jsp");
        
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
