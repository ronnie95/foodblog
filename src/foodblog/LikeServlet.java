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
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		System.out.println("id="+id);
		ArrayList<PostBean> PB=(ArrayList<PostBean>) request.getSession(false).getAttribute("posts");
		PostBean post1=null;
		for(PostBean obj: PB)
		{
			if(obj.getId().equals(id)) {
				post1=obj;
				break;
			}
			
		}
		
		
		LikesBean likeobj=PostDAO.setLikes(id,((UserBean)request.getSession(false).getAttribute("currentSessionUser")).getUsername());
		System.out.println("after likeobj");
		//do stuff
		if(likeobj!=null)
		{
			ArrayList<LikesBean> L=post1.getLikesObj();
			if(L==null) L=new ArrayList<LikesBean>();
			L.add(likeobj);
			post1.setLikesObj(L);
			post1.setLikes(post1.getLikes()+1);
			HttpSession session = request.getSession(true);	    
	        session.setAttribute("posts",PB);
		}
		
		response.sendRedirect("userLogged.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
