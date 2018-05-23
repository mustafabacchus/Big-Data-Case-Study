package com.cdw.runner;

import java.util.ArrayList;
import java.util.Scanner;

import com.cdw.dao.AbstractDAO;
import com.cdw.dao.CustomerDAO;
import com.cdw.dao.TransactionDAO;
import com.cdw.model.Customer;
import com.cdw.model.Transaction;

public class Runner extends AbstractDAO {
	public static void main(String[] args) {
		while (true) {
			System.out.println("ACTIONS AVAILABLE:");
			//1.1.1
			System.out.println("1. Display the transactions made by customers living in a given zipcode for a given month and year.");
			System.out.println("2. Display the number and total values of transactions for a given type.");
			System.out.println("3. Display the number and total values of transactions for branches in a given state.");
			//1.1.2
			System.out.println("4. Display the existing account details of a customer.");
			System.out.println("5. Modify the existing account details of a customer.");
			System.out.println("6. Generate monthly bill for a credit card number for a given month and year.");
			System.out.println("7. Display the transactions made by a customer between two dates.");
			System.out.println();
			Scanner scan = new Scanner(System.in);
			System.out.print("SELECT AN ACTION. ENTER '0' TO QUIT: ");
			int sel = scan.nextInt();
			
			//terminate program 
			if (sel == 0) {System.out.println("GOODBYE.");  scan.close(); System.exit(0);}
			
			
			//1.1.1 (1)
			if (sel == 1) {
				System.out.print("Enter a Zipcode: ");
				String zip = scan.next();
				System.out.print("Enter a Month: ");
				int month = scan.nextInt();
				System.out.print("Enter a Year: ");
				int year = scan.nextInt();
				System.out.println();
				
				//TEST DATA		Zip: 39120 Month: 9 Year: 2018
				TransactionDAO tDAO = new TransactionDAO();
				ArrayList<Transaction> tran = tDAO.getTranByZip(zip, month, year);
				for (Transaction n : tran) {
					System.out.println("ID: " + n.getTranID());
					System.out.println("Day: " + n.getDay());
					System.out.println("Month: " + n.getMonth());
					System.out.println("Year: " + n.getYear());
					System.out.println("Credit NO: " + n.getCreditNo());
					System.out.println("SSN: " + n.getSsn());
					System.out.println("Branch Code: " + n.getBranchCode());
					System.out.println("Transaction Type: " + n.getTranType());
					System.out.println("Transaction Value: " + n.getTranVal());
					System.out.println();
				}
			}
			
			
			//1.1.1 (2)
			if (sel == 2) {
				System.out.print("Enter a Transaction Type: ");
				String type = scan.next();
				System.out.println();
				
				//TEST DATA		Type: Gas
				TransactionDAO tDAO = new TransactionDAO();
				Transaction tran = tDAO.getTranSumByTranType(type);
				System.out.println("Number of Transactions: " + tran.getCount());
				System.out.println("Total Value: " + tran.getTranVal());
			}
			
			
			//1.1.1 (3)
			if (sel == 3) {
				System.out.print("Enter a State Abbreviation: ");
				String state = scan.next().toUpperCase();
				System.out.println();
				
				//TEST DATA		State: IL,il
				TransactionDAO tDAO = new TransactionDAO();
				ArrayList<Transaction> tran = tDAO.getTranSumByState(state);
				for (Transaction n : tran) {
					System.out.println("Bank Name: " + n.getTranType());
					System.out.println("Number of Transactions: " + n.getCount());
					System.out.println("Total Value: " + n.getTranVal());
					System.out.println();
				}
			}
			
			
			
			
			
			//1.1.2 (1)
			if (sel == 4) {
				System.out.print("Enter Customer SSN: ");
				int ssn = scan.nextInt();
				System.out.println();
				
				//TEST DATA		SSN: 123457519
				CustomerDAO cDAO = new CustomerDAO();
				Customer cust = cDAO.getCustomerBySSN(ssn);
				System.out.println("First Name: " + cust.getfName());
				System.out.println("Middle Name: " + cust.getmName());
				System.out.println("Last Name: " + cust.getlName());
				System.out.println("SSN: " + cust.getSsn());
				System.out.println("Credit NO: " + cust.getCreditNo());
				System.out.println("APT NO: " + cust.getAptNo());
				System.out.println("Street: " + cust.getStreet());
				System.out.println("City: " + cust.getStreet());
				System.out.println("State: " + cust.getState());
				System.out.println("Country: " + cust.getCountry());
				System.out.println("Zip: " +  cust.getZip());
				System.out.println("Phone: " + cust.getPhone());
				System.out.println("Email: " + cust.getEmail());
			}
			
			
			
			//1.1.2 (2)
			if (sel == 5) {
				System.out.print("Enter Customer SSN: ");
				int ssn = scan.nextInt();
				scan.nextLine(); //CONSUME END OF INPUT
				System.out.println();
				
				//TEST DATA 	SSN: 123454839
				//Print out customer current info
				System.out.println("CURRENT INFO");
				CustomerDAO cDAO = new CustomerDAO();
				Customer cust = cDAO.getCustomerBySSN(ssn);
				System.out.println("First Name: " + cust.getfName());
				System.out.println("Middle Name: " + cust.getmName());
				System.out.println("Last Name: " + cust.getlName());
				System.out.println("SSN: " + cust.getSsn());
				System.out.println("Credit NO: " + cust.getCreditNo());
				System.out.println("APT NO: " + cust.getAptNo());
				System.out.println("Street: " + cust.getStreet());
				System.out.println("City: " + cust.getCity());
				System.out.println("State: " + cust.getState());
				System.out.println("Country: " + cust.getCountry());
				System.out.println("Zip: " +  cust.getZip());
				System.out.println("Phone: " + cust.getPhone());
				System.out.println("Email: " + cust.getEmail());
				System.out.println();
				
				//User input changes for customer; 0 to skip
				System.out.println("CHANGES FOR " + cust.getlName() + ", " + cust.getfName() + ". ENTER '0' TO SKIP:");
				System.out.print("Change for First Name: "); String fName = scan.nextLine(); if (fName.equals("0")) fName = cust.getfName();
				System.out.print("Change for Middle Name: "); String mName = scan.nextLine(); if (mName.equals("0")) mName = cust.getmName();
				System.out.print("Change for Last Name: "); String lName = scan.nextLine(); if (lName.equals("0")) lName = cust.getlName();
				System.out.print("Change for Apt NO: "); String apt = scan.nextLine(); if (apt.equals("0")) apt = cust.getAptNo();
				System.out.print("Change for Street: "); String street = scan.nextLine(); if (street.equals("0")) street = cust.getStreet();
				System.out.print("Change for City: "); String city = scan.nextLine(); if (city.equals("0")) city = cust.getCity(); 
				System.out.print("Change for State (ST): "); String st = scan.next().toUpperCase(); if (st.equals("0")) st = cust.getState(); 
				scan.nextLine(); //CONSUME END OF IMPUT
				System.out.print("Change for Country: "); String country = scan.nextLine(); if (country.equals("0")) country = cust.getCountry();
				System.out.print("Change for Phone: "); int phone = scan.nextInt(); if (phone == 0) phone = cust.getPhone();
				System.out.print("Change for Email: "); String email = scan.next(); if (email.equals("0")) email = cust.getEmail();
				
				//Cancel changes and terminate
				System.out.println();
				System.out.print("Would you like to commit the above changes? (y/n): ");
				if (scan.next().toLowerCase().equals("n")) {
					System.out.println("CHANGES CANCELED!");
				} else {
				//Modify customer info in database
				CustomerDAO mCDAO = new CustomerDAO();
				Customer mCust = mCDAO.modifyCustomer(fName, mName, lName, apt, street, city, st, country, phone, email, ssn);
				
				//Print out modified customer
				cust = cDAO.getCustomerBySSN(ssn);
				System.out.println();
				System.out.println("MODIFIED INFO:");
				System.out.println("First Name: " + cust.getfName());
				System.out.println("Middle Name: " + cust.getmName());
				System.out.println("Last Name: " + cust.getlName());
				System.out.println("SSN: " + cust.getSsn());
				System.out.println("Credit NO: " + cust.getCreditNo());
				System.out.println("APT NO: " + cust.getAptNo());
				System.out.println("Street: " + cust.getStreet());
				System.out.println("City: " + cust.getCity());
				System.out.println("State: " + cust.getState());
				System.out.println("Country: " + cust.getCountry());
				System.out.println("Zip: " +  cust.getZip());
				System.out.println("Phone: " + cust.getPhone());
				System.out.println("Email: " + cust.getEmail());
				}
			}
			
			
			//1.1.2 (3)
			if (sel == 6) {
				System.out.print("Enter Card NO: ");
				String card = scan.next();
				System.out.print("Enter a Month: ");
				int month = scan.nextInt();
				System.out.print("Enter a Year: ");
				int year = scan.nextInt();
				System.out.println();
				
				//TEST DATA 	Card: 4210653349028689 Month: 1 Year: 2018
				CustomerDAO cDAO = new CustomerDAO();
				Customer cust = cDAO.getCreditBillByMonthYear(card, month, year);
				System.out.println("Bill: " + cust.getSum());
			}
			
			
			//1.1.2 (4)
			if (sel == 7) {
				System.out.print("Enter Customer SSN: ");
				int ssn = scan.nextInt();
				System.out.print("Enter 1st Date (MM/DD/YYYY): ");
				String date1 = scan.next();
				System.out.print("Enter 2nd Date (MM/DD/YYYY): ");
				String date2 = scan.next();
				System.out.println();
				
				
				//TEST DATA 	SSN: 123454839 Date1: 3/1/2017 Date2: 8/31/2018
				CustomerDAO cDAO = new CustomerDAO();
				ArrayList<Transaction> tran = cDAO.getTranByCustDate(ssn, date1, date2);
				for (Transaction n: tran) {
					System.out.println("Transaction ID: " + n.getTranID());
					System.out.println("Day: " + n.getDay());
					System.out.println("Month: " + n.getMonth());
					System.out.println("Year: " + n.getYear());
					System.out.println("Credit NO: " + n.getCreditNo());
					System.out.println("Branch Code: " + n.getBranchCode());
					System.out.println("Transaction Type: " + n.getTranType());
					System.out.println("Transaction Value: " + n.getTranVal());
					System.out.println();
				}
			}
		
			System.out.println(); System.out.println();
		}//end of while loop
	} //end of main
}