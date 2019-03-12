package foodblog;

import java.util.*;

public class PostBean {
	
    private String id;
    private String text;
    private UserBean user;
    private long likes;
    private ArrayList<ImageBean> images;
    private ArrayList<CommentsBean> comments;
    private ArrayList<LikesBean> likesObj;
    private String ts;
    
	
	
    public String getId() {
       return id;
	}

    public void setId(String newId) {
       id = newId;
	}


    public String getText() {
       return text;
	}

    public void setText(String newText) {
       text=newText;
	}
    

    public UserBean getUser() {
       return user;
	}

    public void setUser(UserBean newUser) {
       user=newUser;
	}
    

    public long getLikes() {
       return likes;
	}

    public void setLikes(long newLikes) {
       likes = newLikes;
	}
    

    public ArrayList<ImageBean> getImages() {
       return images;
	}

    public void setImages(ArrayList<ImageBean> newImages) {
       images = newImages;
	}
   
    public ArrayList<CommentsBean> getComments() {
        return comments;
 	}

     public void setComments(ArrayList<CommentsBean> newComments) {
        comments = newComments;
 	}
     
     
     public ArrayList<LikesBean> getLikesObj() {
         return likesObj;
  	}

      public void setLikesObj(ArrayList<LikesBean> newLikesObj) {
         likesObj = newLikesObj;
  	}
      
      public String getTs() {
          return ts;
   	}

       public void setTs(String n) {
          ts = n;
   	}
}
