package com.cdw.runner;

import java.util.ArrayList;
import java.util.Scanner;

import com.cdw.dao.CustomerDAO;
import com.cdw.model.Customer;
import com.cdw.model.Transaction;

public class CustomerRunner extends Main{

	private static Scanner scan = new Scanner(System.in);
	
	
	//4
	public void customerBySSN() {
		try {
			System.out.print("Enter Customer SSN: ");
			String ssn = scan.next();
			System.out.println();
			
			//TEST DATA		SSN: 123456100
			printCustomerBySSN(ssn);
			export();
		} catch (java.lang.Exception e) {
			System.out.println("NO DATA.");
		}
	}
	
	
	//5
	public void modifyCustomer() {
		System.out.print("Enter Customer SSN: ");
		String ssn = scan.next();
		scan.nextLine(); //CONSUME END OF INPUT
		System.out.println();
		
		//TEST DATA 	SSN: 123454839
		//Print out customer current info
		System.out.println("CURRENT INFO:");
		printCustomerBySSN(ssn);

		CustomerDAO cDAO = new CustomerDAO();
		Customer cust = cDAO.getCustomerInfo(Integer.parseInt(ssn));
		//User input changes for customer
		System.out.println("CHANGES FOR " + cust.getlName() + ", " + cust.getfName() + ". ENTER '0' TO SKIP:");
		System.out.print("Change for First Name: "); String fName = scan.nextLine(); if (fName.equals("0")) fName = cust.getfName();
		System.out.print("Change for Middle Name: "); String mName = scan.nextLine(); if (mName.equals("0")) mName = cust.getmName();
		System.out.print("Change for Last Name: "); String lName = scan.nextLine(); if (lName.equals("0")) lName = cust.getlName();
		System.out.print("Change for Apt NO: "); String apt = scan.nextLine(); if (apt.equals("0")) apt = cust.getAptNo();
		System.out.print("Change for Street: "); String street = scan.nextLine(); if (street.equals("0")) street = cust.getStreet();
		System.out.print("Change for City: "); String city = scan.nextLine(); if (city.equals("0")) city = cust.getCity(); 
		System.out.print("Change for State (st): "); String st = scan.next().toUpperCase(); if (st.equals("0")) st = cust.getState(); 
		scan.nextLine(); //CONSUME END OF INPUT
		System.out.print("Change for Zip: "); String zip = scan.next(); if (zip.equals("0")) zip = cust.getZip(); 
		scan.nextLine(); //CONSUME END OF INPUT
		System.out.print("Change for Country: "); String country = scan.nextLine(); if (country.equals("0")) country = cust.getCountry();
		System.out.print("Change for Phone: "); String phone = scan.next(); if (phone.equals("0")) phone = String.valueOf(cust.getPhone());
		System.out.print("Change for Email: "); String email = scan.next(); if (email.equals("0")) email = cust.getEmail();
		
		//Cancel changes and terminate
		System.out.println();
		System.out.print("Would you like to commit the above changes? (y/n): ");
		if (scan.next().toLowerCase().equals("y")) {
			System.out.println();
			System.out.println("CUSTOMER WITH CHANGES:");
			//Modify customer in database
			CustomerDAO mCDAO = new CustomerDAO();
			@SuppressWarnings("unused")
			Customer mCust = mCDAO.modifyCustomer(fName, mName, lName, apt, street, city, st, country, zip, Integer.parseInt(phone), email, Integer.parseInt(ssn));
			//print customer with changes
			printCustomerBySSN(ssn);
		} else {
			System.out.println("CHANGES CANCELED!");
		}
	}
	
	
	
	//6
	public void billByDate() {
		try {
			System.out.print("Enter Card NO: ");
			String card = scan.next();
			System.out.print("Enter a Month: ");
			String month = scan.next();
			System.out.print("Enter a Year: ");
			String year = scan.next();
			System.out.println();
			
			//TEST DATA 	Card: 4210653349028689 Month: 1 Year: 2018
			CustomerDAO cDAO = new CustomerDAO();
			Customer cust = cDAO.getCreditBillByMonthYear(card, Integer.parseInt(month), Integer.parseInt(year));
			System.out.println("Bill: " + cust.getSum());
			System.out.println();
			
			output += cust.getSum();
			header1 += "Card: " + card + headDel + "Month: " + month + headDel + "Year " + year;
			header2 += "Bill";
			export();
		} catch (java.lang.Exception e) {
			System.out.println("NO DATA.");
		}
	}
	
	
	
	//7
	public void sumByDate() {
		try {
			System.out.print("Enter Customer SSN: ");
			String ssn = scan.next();
			System.out.print("Enter 1st Date (MM/DD/YYYY): ");
			String date1 = scan.next();
			System.out.print("Enter 2nd Date (MM/DD/YYYY): ");
			String date2 = scan.next();
			System.out.println();
			
			//TEST DATA 	SSN: 123454839 Date1: 3/1/2017 Date2: 8/31/2018
			CustomerDAO cDAO = new CustomerDAO();
			ArrayList<Transaction> tran = cDAO.getTranByCustDate(Integer.parseInt(ssn), date1, date2);
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
				
				output += n.getTranID() + del + n.getDay() + del + n.getMonth() + del + n.getYear() + del + n.getCreditNo() + del + n.getBranchCode() + del + n.getTranType()
					+ del + n.getTranVal() + "\n";
			}
			header1 += "SSN: " + ssn + headDel + "Between: " + date1 + " - " + date2;
			header2 += "ID" + del + "Day" + del + "Month" + del + "Year" + del + "CreditNO" + del + "Branch Code" + del + "Type" + del + "Value";
			export();
		} catch (java.lang.Exception e) {
			System.out.println("NO DATA.");
		}
	}
	
	
	
	private void printCustomerBySSN(String ssn) {
		CustomerDAO cDAO = new CustomerDAO();
		ArrayList<Customer> cust = cDAO.getCustomerBySSN(Integer.parseInt(ssn));
		for (Customer n : cust) {
		System.out.println("First Name: " + n.getfName());
		System.out.println("Middle Name: " + n.getmName());
		System.out.println("Last Name: " + n.getlName());
		System.out.println("SSN: " + n.getSsn());
		System.out.println("Credit NO: " + n.getCreditNo());
		System.out.println("APT NO: " + n.getAptNo());
		System.out.println("Street: " + n.getStreet());
		System.out.println("City: " + n.getCity());
		System.out.println("State: " + n.getState());
		System.out.println("Country: " + n.getCountry());
		System.out.println("Zip: " +  n.getZip());
		System.out.println("Phone: " + n.getPhone());
		System.out.println("Email: " + n.getEmail());
		System.out.println();
		
		output += n.getfName() + del + n.getmName() + del + n.getlName() + del + n.getSsn() + del + n.getCreditNo() + del + n.getAptNo() + del + n.getStreet()
			+ del + n.getCity() + del + n.getState() + del + n.getCountry() + del + n.getZip() + del + n.getPhone() + del + n.getEmail() + "\n";
		}
		header1 += "SSN: " + ssn;
		header2 += "First Name" + del + "Middle Name" + del + "Last Name" + del + "SSN" + del + "CreditNO" + del + "APT NO" + del + "Street" + del + "City" + del + "State" + "Country" 
				+ del + "Zip" + del + "Phone" + del + "Email";
	}
}