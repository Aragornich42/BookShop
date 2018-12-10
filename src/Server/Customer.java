package Server;

import java.util.regex.Pattern;

public class Customer implements WorkWithBaseClasses {

	private int id;
	private String fullName;
	private String contacts;
	private String orders;
	
	public Customer() {
		id = 0;
		fullName = "";
		contacts = "";
		orders = "";
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getContacts() {
		return contacts;
	}
	
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getOrders() {
		return orders;
	}
	
	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	//������ ������
	@Override
	public String[] Parser(String prsStr) {
		return prsStr.split(Pattern.quote("|"));
	}
	
	public Customer(String prsStr) {
		String[] str = Parser(prsStr);
		fullName = str[0];
		contacts = str[1];
		orders = str[2];
	}
	
	//�������� ������ ����������� ������
	public String CustomerToString() {
		String[] conts = ParseContacts();
		return "id = " + id + ", " + fullName + "; " + conts[0] + ", " + conts[1] + ", " 
				+ conts[2] + "; " + orders + ".\n";
	}
	
	private String[] ParseContacts() {
		return contacts.split(Pattern.quote(";"));
	}
	
}
