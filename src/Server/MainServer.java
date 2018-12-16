package Server;

//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
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
		}
	}

	public static Customer FindCust(String name) {
		int i = 0;
		while(true) {
			if(customers.elementAt(i).getFullName().equals(name))
				return customers.elementAt(i);
			i++;
		}
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
			//BufferedReader buffRe = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//PrintWriter printWr = new PrintWriter(client.getOutputStream());
			DataInputStream buffRe = new DataInputStream(client.getInputStream());
			DataOutputStream printWr = new DataOutputStream(client.getOutputStream());
			FileParser fp = new FileParser();
			DBWorker db = new DBWorker();
			
			System.out.println("It's time to work!");
			books = fp.ParserBooks();
			customers = fp.ParserCustomers();
			orders = fp.ParserOrders();
			printWr.writeUTF('\n' + BooksString());
			printWr.writeUTF('\n' + CustomersString());
			printWr.writeUTF('\n' + OrdersString());
			printWr.flush();
			System.out.println("We did it!");
			
			String command, info;
			while(!(command = buffRe.readUTF()).equals("END")) {
				info = buffRe.readUTF();
				switch(command) {
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
					db.AddOrder(orders, tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5]);
					printWr.writeUTF("\nOrder added");
					printWr.flush();
					break;
				case "DELB":
					db.DeleteBook(books, info);
					printWr.writeUTF("\nBook deleted");
					printWr.flush();
					break;
				case "DELC":
					db.DeleteCustomer(customers, info);
					printWr.writeUTF("\nCustomer deleted");
					printWr.flush();
					break;
				case "CHAB":
					tmp = ServerOwnParser(info);
					db.ChangeBook(FindBook(tmp[0]), tmp[1], tmp[2]);
					printWr.writeUTF("\nBook changed");
					printWr.flush();
					break;
				case "CHAC":
					tmp = ServerOwnParser(info);
					db.ChangeCustomer(FindCust(tmp[0]), tmp[1], tmp[2]);
					printWr.writeUTF("\nCustomer changed");
					printWr.flush();
					break;
				case "CHEST":
					printWr.writeUTF(db.CheckStatus(orders, info));
					printWr.flush();
					break;
				case "CHAST":
					tmp = ServerOwnParser(info);
					db.ChangeStatus(orders, tmp[0], tmp[1]);
					printWr.writeUTF("Status changed");
					printWr.flush();
					break;
				case "CHEB":
					tmp = ServerOwnParser(info);
					printWr.writeUTF(db.CheckBooks(books, tmp[0], tmp[1]));
					printWr.flush();
					break;
				case "CHEC":
					if(db.isUser(customers, info))
						printWr.writeUTF("User found");
					else
						printWr.writeUTF("User not found");
					printWr.flush();
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
