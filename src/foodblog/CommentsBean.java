package foodblog;

public class CommentsBean {
	
	//private PostBean post;
	private UserBean user;
	private int id;
	private String text;
	
//	public PostBean getPost()
//	{
//		return post;
//	}
//	
//	public void setPost(PostBean newPost)
//	{
//		post=newPost;
//	}
	
	public UserBean getUser()
	{
		return user;
	}
	
	public void setUser(UserBean newUser)
	{
		user=newUser;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int newId)
	{
		id=newId;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String newText)
	{
		text=newText;
	}

}
