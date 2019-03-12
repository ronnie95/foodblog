package foodblog;
import java.util.*;
public class RestaurantBean {
	
    private String resName;
    private String password;
    private String location;
    public int seats;
	public ArrayList<MenuBean> M;
	
    public String getResName() {
       return resName;
	}

    public void setresName(String newResName) {
       resName = newResName;
	}

			

    public String getPassword() {
       return password;
	}

    public void setPassword(String newPassword) {
       password = newPassword;
	}
	
			
    public String getLocation() {
       return location;
			}

    public void setLocation(String newLocation) {
       location = newLocation;
			}

				
    public ArrayList<MenuBean> getMenu() {
       return M;
	}

    public void setMenu(ArrayList<MenuBean> newM) {
       M = newM;
	}	
    
    public int getSeats() {return seats;}
    public void setSeats(int s) {seats=s;}
}
