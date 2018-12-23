package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.regex.Pattern;

public class MainServer {
	
	private static Vector<Book> books;
	private static Vector<Customer> customers;
	private static Vector<Order> orders;
	
	private static Book FindBook(String name) {
		int i = 0;
		while(true) {
			if(books.elementAt(i).getName().equals(name))
				return books.elementAt(i);
			i++;
			if(i == books.size())
				return null;
		}
	}

	public static Customer FindCust(String name) {
		int i = 0;
		while(true) {
			if(customers.elementAt(i).getFullName().equals(name))
				return customers.elementAt(i);
			i++;
			if(i == customers.size())
				return null;
		}
	}

	public static String[] FindCustEmail(String email) {
		String[] tmp = new String[2];
		int i = 0;
		while(true) {
			if(customers.elementAt(i).ParseContacts()[2].equals(email)) {
				tmp[0] = customers.elementAt(i).getFullName();
				tmp[1] = customers.elementAt(i).ParseContacts()[0];
				return tmp;
			}
			i++;
			if(i == customers.size())
				return null;
		}
	}

	public static String CheckPrice(String name, String count) {
		Book b = FindBook(name);
		double d = Double.parseDouble(b.getPrice()) * Double.parseDouble(count);
		return String.valueOf(d);
	}
	
	public static String[] ServerOwnParser(String info) {
		return info.split(Pattern.quote("|"));
	}

	public static String BooksString() {
		String tmp = "";
		for (Book book : books) tmp += book.BookToString();
		return tmp;
	}

	public static String CustomersString() {
		String tmp = "";
		for (Customer customer : customers) tmp += customer.CustomerToString();
		return tmp;
	}

	public static String OrdersString() {
		String tmp = "";
		for (Order order : orders) tmp += order.OrderToString();
		return tmp;
	}
	
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(8080);
			System.out.println("Wait...");
			Socket client = server.accept();
			System.out.println("Client is ready!");
			DataInputStream buffRe = new DataInputStream(client.getInputStream());
			DataOutputStream printWr = new DataOutputStream(client.getOutputStream());
			FileParser fp = new FileParser();
			DBWorker db = new DBWorker();
			
			System.out.println("It's time to work!");
			books = fp.ParserBooks();
			customers = fp.ParserCustomers();
			orders = fp.ParserOrders();
			System.out.println("We did it!");
			
			String command = "NEXT", info;
			while (!command.equals("END")) {
				try {
					command = buffRe.readUTF();
					info = buffRe.readUTF();
					switch (command) {
						case "LIST":
							printWr.writeUTF('\n' + BooksString() + '\n' + CustomersString() + '\n'
									+ OrdersString());
							printWr.flush();
							break;
						case "LISTB":
							printWr.writeUTF('\n' + BooksString());
							printWr.flush();
							break;
						case "LISTC":
							printWr.writeUTF('\n' + CustomersString());
							printWr.flush();
							break;
						case "LISTO":
							printWr.writeUTF('\n' + OrdersString());
							printWr.flush();
							break;
						case "ADDB":
							String[] tmp = ServerOwnParser(info);
							db.AddBook(books, tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5]);
							printWr.writeUTF("\nBook added");
							printWr.flush();
							break;
						case "ADDC":
							tmp = ServerOwnParser(info);
							db.AddCustomer(customers, tmp[0], tmp[1], tmp[2]);
							printWr.writeUTF("\nCustomer added");
							printWr.flush();
							break;
						case "ADDO":
							tmp = ServerOwnParser(info);
							String name = buffRe.readUTF();
							String count = buffRe.readUTF();
							tmp[2].replaceAll(Pattern.quote("~"), CheckPrice(name, count));
							db.AddOrder(orders, tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5]);
							printWr.writeUTF("\nOrder added");
							printWr.flush();
							break;
						case "DELB":
							if(db.DeleteBook(books, info))
								printWr.writeUTF("\nBook deleted");
							else
								printWr.writeUTF("\nFail");
							printWr.flush();
							break;
						case "DELC":
							if(db.DeleteCustomer(customers, info))
								printWr.writeUTF("\nCustomer deleted");
							else
								printWr.writeUTF("\nFail");
							printWr.flush();
							break;
						case "CHAB":
							tmp = ServerOwnParser(info);
							if(FindBook(tmp[0]) != null) {
								db.ChangeBook(FindBook(tmp[0]), tmp[1], tmp[2]);
								printWr.writeUTF("\nBook changed");
							} else   printWr.writeUTF("\nFail");
							printWr.flush();
							break;
						case "CHAC":
							tmp = ServerOwnParser(info);
							if(FindCust(tmp[0]) != null) {
								db.ChangeCustomer(FindCust(tmp[0]), tmp[1], tmp[2]);
								printWr.writeUTF("\nCustomer changed");
							} else   printWr.writeUTF("\nFail");
							printWr.flush();
							break;
						case "CHEST":
							if(db.CheckStatus(orders, info) != null)
								printWr.writeUTF(db.CheckStatus(orders, info));
							else
								printWr.writeUTF("\nFail");
							printWr.flush();
							break;
						case "CHAST":
							tmp = ServerOwnParser(info);
							if(db.ChangeStatus(orders, tmp[0], tmp[1]))
								printWr.writeUTF("Status changed");
							else
								printWr.writeUTF("\nFail");
							printWr.flush();
							break;
						case "CHEB":
							tmp = ServerOwnParser(info);
							printWr.writeUTF(db.CheckBooks(books, tmp[0], tmp[1]));
							printWr.flush();
							break;
						case "CHEC":
							if (db.isUser(customers, info)) {
								printWr.writeUTF("User found");
								String[] ret = FindCustEmail(info);
								if(ret == null)
									printWr.writeUTF("\nFail");
								else {
									printWr.writeUTF(ret[0]);
									printWr.writeUTF(ret[1]);
								}
							} else
								printWr.writeUTF("User not found");
							printWr.flush();
						default:
							continue;
						}
					} catch (EOFException e) {
						continue;
					}
				}



			System.out.println("Close");
			printWr.close();
			buffRe.close();
			client.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
