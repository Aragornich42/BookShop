package Server;

import java.util.regex.Pattern;

public class Order implements WorkWithBaseClasses {

	private int id;
	private String date;
	private String fullName;
	private String orderAndPrice;
	private String contacts;
	private String time;
	private String status;
	
	public Order() {
		id = 0;
		fullName = "";
		date = "";
		orderAndPrice = "";
		contacts = "";
		time = "";
		status = "";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getOrderAndPrice() {
		return orderAndPrice;
	}
	
	public void setOrderAndPrice(String orderAndPrice) {
		this.orderAndPrice = orderAndPrice;
	}
	
	public String getContacts() {
		return contacts;
	}
	
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String[] Parser(String prsStr) {
		return prsStr.split(Pattern.quote("|"));
	}
	
	public Order(String prsStr) {
		String[] str = Parser(prsStr);
		date = str[0];
		fullName = str[1];
		orderAndPrice = str[2];
		contacts = str[3];
		time = str[4];
		status = str[5];
	}

	public String OrderToString() {
		return "id = " + id + ", " + date + "; " + fullName + "; " + orderAndPrice + "; " 
				+ contacts + "; " + time + "; " + status + ".\n";
	}
	
}
