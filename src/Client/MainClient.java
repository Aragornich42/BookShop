package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClient {
	
	static void Menu() {
		System.out.println("\nPlease, select an action and input command:");
		System.out.println("END: End the programm.");
		System.out.println("LIST: Return all database.");
		System.out.println("LISTB: Return books database.");
		System.out.println("LISTC: Return customers database.");
		System.out.println("LISTO: Return orders database.");
		System.out.println("ADDB: Add book.");
		System.out.println("ADDC: Add customer.");
		System.out.println("ADDO: Add order.");
		System.out.println("DELB: Delete book.");
		System.out.println("DELC: Delete customer.");
		System.out.println("CHAB: Change book data.");
		System.out.println("CHAC: Change customer data.");
		System.out.println("CHEST: Check order status.");
		System.out.println("CHAST: Change order status.");
		System.out.println("CHEB: Select books you need.");
	}
	
	private static boolean YesNo(String charact) {
		Scanner sc = new Scanner(System.in);
		String symbol;
		while(true) {
			System.out.print("Do you want " + charact + "? +/-: ");
			if((symbol = sc.nextLine()).equals("+"))
				return true;
			else if (symbol.equals("-"))
				return false;
			else
				System.out.println("Invalid letter. Please, repeat.");
		}
	}
	
	private static void BookSpec() {
		System.out.println("\nNice! Read specification before data input:");
		System.out.println("NAME - if you need in book name");
		System.out.println("AUTHOR - if you need in authors");
		System.out.println("GENRE - if you need in genre");
		System.out.println("PUBLISH - if you need in publisher");
		System.out.println("DATE - if you need in year of publishing");
		System.out.println("PAGES - if you need in pages count");
		System.out.println("COVER - if you need in cover type");
		System.out.println("PRICE - if you need in price");
		System.out.println("COUNT - if you need in count\n");
	}
	
	private static void CustSpec() {
		System.out.println("\nNice! Read specification before data input:");
		System.out.println("NAME - if you need in full name");
		System.out.println("CONTACTS - if you need in contacts");
		System.out.println("ORDERS - if you need in orders\n");
	}
//TODO: Хранить каким-то образом инфу о клиенте. Нужны ФИО, адрес, возможно - действительная дата работы.
	public static void main(String[] args) throws IOException {
		System.out.println("Start...");
		Socket server = new Socket("localhost", 8080);
		System.out.println("Yeah!");
		//PrintWriter printWr = new PrintWriter(server.getOutputStream());
		//BufferedReader buffRe = new BufferedReader(new InputStreamReader(server.getInputStream()));
		DataInputStream buffRe = new DataInputStream(server.getInputStream());
		DataOutputStream printWr = new DataOutputStream(server.getOutputStream());
		Scanner scan = new Scanner(System.in);
		Pattern patt = Pattern.compile("END|LIST|LISTB|LISTC|LISTO|ADDB|ADDC|ADDO|DELB|DELC|CHAB|"
				+ "CHAC|CHEST|CHAST|CHEB");
		Matcher math;
		
		System.out.println("Start reading...");
		//String tmp;
		//while((tmp = buffRe.readUTF()) != null)
		System.out.println(buffRe.readUTF());
		System.out.println(buffRe.readUTF());
		System.out.println(buffRe.readUTF());
		System.out.println();
		System.out.println("That's all.");
		
		String command = "READY", info;
		Menu();
		while(!command.equals("END")) {
			System.out.print("Print command: ");
			command = scan.nextLine();
			if(command.equals("END")) {
				printWr.writeUTF("END");
				break;
			}
			while(true) {
				math = patt.matcher(command);
				if(!math.matches()) {
					System.out.print("Invalid command. Please, repeat: ");
					command = scan.nextLine();
				}
				else break;
			}
			if(command.equals("CHEB") || command.equals("CHAB"))
				BookSpec();
			if(command.equals("CHAC"))
				CustSpec();
			System.out.println("Please, input data:");
			info = scan.nextLine();
			
			printWr.writeUTF(command);
			printWr.writeUTF(info);
			
			System.out.println(buffRe.readUTF());
			
			System.out.println();
			if(!YesNo("continue")) {
				command = "END";
				printWr.writeUTF(command);
			}
			else
				if(YesNo("Menu"))
					Menu();
		}
		
		
		System.out.println("It's time to sleep...");
		buffRe.close();
		printWr.close();
		scan.close();
		server.close();
	}

}
