package foodblog;

public class RequestOrder {

	private int id;
	private String uid;
	private String rid;
	private int uvalid,rvalid;
	private String orderlist;
	
	public void setId(int i) {id=i;}
	public int getId() {return id;}
	
	public void setUid(String i) {uid=i;}
	public String getUid() {return uid;}
	
	public void setRid(String i) {rid=i;}
	public String getRid() {return rid;}
	
	public void setUvalid(int i) {uvalid=i;}
	public int getUvalid() {return uvalid;}
	
	
	public void setRvalid(int i) {rvalid=i;}
	public int getRvalid() {return rvalid;}
	
	public void setOrderList(String i) {orderlist=i;}
	public String getOrderList() {return orderlist;}
}
