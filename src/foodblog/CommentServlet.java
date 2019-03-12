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
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String comment=request.getParameter("comment");
		System.out.println("id="+id+comment);
		ArrayList<PostBean> PB=(ArrayList<PostBean>) request.getSession(false).getAttribute("posts");
		PostBean post1=null;
		for(PostBean obj: PB)
		{
			if(obj.getId().equals(id)) {
				post1=obj;
				break;
			}
			
		}
		
		
		CommentsBean commentobj=PostDAO.setComment(id,((UserBean)request.getSession(false).getAttribute("currentSessionUser")).getUsername(),comment);
		System.out.println("after likeobj");
		//do stuff
		
			ArrayList<CommentsBean> C=post1.getComments();
			if(C==null) C=new ArrayList<CommentsBean>();
			C.add(commentobj);
			post1.setComments(C);
			
			HttpSession session = request.getSession(true);	    
	        session.setAttribute("posts",PB);
		
		
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
