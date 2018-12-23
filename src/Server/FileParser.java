package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class FileParser {

	public Vector<Book> ParserBooks() throws IOException {
		FileReader file = new FileReader("Books.txt");
		BufferedReader buff = new BufferedReader(file);
		String prsStr;
		int i = 0;
		Vector<Book> books = new Vector<>();
		
		while((prsStr = buff.readLine()) != null) {
			books.add(new Book(prsStr));
			books.elementAt(i).setId(++i);
		}
		file.close();
		buff.close();
		
		return books;
	}

	public Vector<Customer> ParserCustomers() throws IOException {
		FileReader file = new FileReader("Customers.txt");		
		BufferedReader buff = new BufferedReader(file);
		String prsStr;
		int i = 0;
		Vector<Customer> customers = new Vector<>();
		
		while((prsStr = buff.readLine()) != null) {
			customers.add(new Customer(prsStr));
			customers.elementAt(i).setId(++i);
		}
		
		file.close();
		buff.close();
		
		return customers;
	}

	public Vector<Order> ParserOrders() throws IOException {
		FileReader file = new FileReader("Orders.txt");		
		BufferedReader buff = new BufferedReader(file);
		String prsStr;
		int i = 0;
		Vector<Order> orders = new Vector<>();
		
		while((prsStr = buff.readLine()) != null) {
			orders.add(new Order(prsStr));
			orders.elementAt(i).setId(++i);
		}

		file.close();
		buff.close();
		
		return orders;
	}

}
