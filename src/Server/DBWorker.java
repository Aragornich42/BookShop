package Server;

import java.util.Vector;

class DBWorker {
	
	//������� ������������ ������ �� ������� ���� �� �������, ������, ������������,
	//���� �������, ���������� �������, ���� �������, ���� � ������� ����.
	//SPECIFICATION - ��� �������. ����� ����: AUTHOR, GENRE, PUBLISH, DATE, PAGES,
	//COVER, PRICE, COUNT
	public String CheckBooks(Vector<Book> books, String SPECIFICATION, String value) {
		StringBuilder result = new StringBuilder("\n\n");
		switch(SPECIFICATION) {
		case "AUTHOR":
			for(Book bk : books)
				if(bk.getAuthors().equals(value))
					result.append(bk.BookToString());
			break;
		case "GENRE":
			for(Book bk : books)
				if(bk.getGenre().equals(value))
					result.append(bk.BookToString());
			break;
		case "PUBLISH":
			for(Book bk : books)
				if(bk.ParseInfo()[0].equals(value))
					result.append(bk.BookToString());
			break;
		case "DATE":
			for(Book bk : books)
				if(bk.ParseInfo()[1].equals(value))
					result.append(bk.BookToString());
			break;
		case "PAGES":
			for(Book bk : books)
				if(bk.ParseInfo()[2].equals(value))
					result.append(bk.BookToString());
			break;
		case "COVER":
			for(Book bk : books)
				if(bk.ParseInfo()[3].equals(value))
					result.append(bk.BookToString());
			break;
		case "PRICE":
			for(Book bk : books)
				if(bk.getPrice().equals(value))
					result.append(bk.BookToString());
			break;
		case "COUNT":
			for(Book bk : books)
				if(bk.getCount().equals(value))
					result.append(bk.BookToString());
			break;
		default:
			System.out.println("INVALID SPECIFICATION!");
			return "";
		}
		return result + "\n\n";
	}
	
	//������� ���������� ������
	public void AddOrder(Vector<Order> orders, String date, String fullName, 
			String orderAndPrice, String contacts, String time, String status) {
		Order ord = new Order();
		ord.setContacts(contacts);
		ord.setDate(date);
		ord.setFullName(fullName);
		ord.setOrderAndPrice(orderAndPrice);
		ord.setStatus(status);
		ord.setTime(time);
		ord.setId(orders.size() + 1);
		orders.add(ord);
	}
	
	//�������� ������� ������
	public String CheckStatus(Vector<Order> orders, String fullName) {
		int i = 0;
		while(true) {
			if(orders.elementAt(i).getFullName().equals(fullName))
				return orders.elementAt(i).getStatus();
			i++;
		}
	}
	
	//��������� ������� ������
	public void ChangeStatus(Vector<Order> orders, String fullName, String status) {
		int i = 0;
		while(true) {
			if(orders.elementAt(i).getFullName().equals(fullName)) {
				orders.elementAt(i).setStatus(status);
				break;
			}
			i++;
		}
	}
	
	//���������� �������
	public void AddCustomer(Vector<Customer> customers, String fullName, 
			String contacts, String orders) {
		Customer cust = new Customer();
		cust.setContacts(contacts);
		cust.setFullName(fullName);
		cust.setOrders(orders);
		cust.setId(customers.size() + 1);
		customers.add(cust);
	}
	
	//�������� �������
	public void DeleteCustomer(Vector<Customer> customers, String fullName) {
		int i = 0;
		while(true) {
			if(customers.elementAt(i).getFullName().equals(fullName)) {
				customers.remove(i);
				break;
			}
			i++;
		}
	}
	
	//�������������� ������ �� �������. SPECIFICATION - NAME, CONTACTS, ORDERS.
	public void ChangeCustomer(Customer customer, String SPECIFICATION, 
			String value) {
		switch(SPECIFICATION) {
		case "NAME":
			customer.setFullName(value);
			break;
		case "CONTACTS":
			customer.setContacts(value);
			break;
		case "ORDERS":
			customer.setOrders(value);
			break;
		default:
			System.out.println("INVALID SPECIFICATION!");
			break;
		}
	}
	
	//���������� �����
	public void AddBook(Vector<Book> books, String name, String authors, String genre, 
			String info, String price, String count) {
		Book book = new Book();
		book.setAuthors(authors);
		book.setCount(count);
		book.setGenre(genre);
		book.setInfo(info);
		book.setName(name);
		book.setPrice(price);
		book.setId(books.size() + 1);
		books.add(book);
	}
		
	//�������� �����
	public void DeleteBook(Vector<Book> books, String name) {
		int i = 0;
		while(true) {
			if(books.elementAt(i).getName().equals(name)) {
				books.remove(i);
				break;
			}
			i++;
		}
	}
		
	//�������������� ������ �� �����. SPECIFICATION - NAME, AUTHOR, GENRE, PUBLISH, DATE, 
	//PAGES, COVER, PRICE, COUNT.
	public void ChangeBook(Book book, String SPECIFICATION, String value) {
		switch(SPECIFICATION) {
		case "AUTHOR":
			book.setAuthors(value);
			break;
		case "GENRE":
			book.setGenre(value);
			break;
		case "PUBLISH":
			String tmp = book.ParseInfo()[0];
			book.setInfo(book.getInfo().replaceFirst(tmp, value));
			break;
		case "DATE":
			tmp = book.ParseInfo()[1];
			book.setInfo(book.getInfo().replaceFirst(tmp, value));
			break;
		case "PAGES":
			tmp = book.ParseInfo()[2];
			book.setInfo(book.getInfo().replaceFirst(tmp, value));
			break;
		case "COVER":
			tmp = book.ParseInfo()[3];
			book.setInfo(book.getInfo().replaceFirst(tmp, value));
			break;
		case "PRICE":
			book.setPrice(value);
			break;
		case "COUNT":
			book.setCount(value);
			break;
		default:
			System.out.println("INVALID SPECIFICATION!");
			break;
		}
	}

}