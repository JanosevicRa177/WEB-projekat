package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import enums.CustomerType;
import enums.Gender;
import model.Customer;
import model.User;

public class CustomerFileStorage {
	
	public Map<String, Customer> customers;
	
	public CustomerFileStorage() {
	}
	
	public String addCustomer(Customer cus) {
		customers = readCustomers();
		customers.put(cus.getUsername(),cus);
		writeCustomers();
		return "Registration successful!";
	}
	
	public void setPoints(String username,double points) {
		customers = readCustomers();
		for (Customer cus : customers.values()) {
			if(cus.getUsername().equals(username)) {
				cus.setPoints(points);
			}
		}
		this.writeCustomers();
	}
	
	public Customer getCustomer(String username) {
		for (Customer cus : this.readCustomers().values()) {
			if(cus.getUsername().equals(username)) {
				return cus;
			}
		}
		return null;
	}
	
	public Boolean changeUser(User user) {
		customers = readCustomers();
		Customer customer = customers.get(user.getUsername());
		customers.remove(user.getUsername());
		customers.put(user.getUsername(), customer.change(user));
		this.writeCustomers();
		return true;
	}
	
	public Boolean isUniqueUsername(String username) {
		customers = readCustomers();
		Customer customer = customers.get(username);
		if(customer == null) return true;
		return false;
	}
	
	public boolean writeCustomers() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("./customers.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : customers.keySet())
			{
				Customer customer = customers.get(key);
				String outputString = "";
				outputString += customer.getUsername() + ";";
				outputString += customer.getPassword() + ";";
				outputString += customer.getName() + ";";
				outputString += customer.getSurname() + ";";
				if(customer.getGender() == Gender.Male)
				outputString += "Male" + ";";
				else if(customer.getGender() == Gender.Female)
				outputString += "Female" + ";";
				else
				outputString += "Alien" + ";";
				outputString += formatter.format(customer.getBirthDate()) + ";";
				outputString += customer.getPoints();
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, Customer> readCustomers() {
		Map<String, Customer> customersInner = new TreeMap<String, Customer>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("./customers.txt");
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "", name = "",surname = "",gender = "", locDate = "", points = "";
			StringTokenizer st;
			try {
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						username = st.nextToken().trim();
						password = st.nextToken().trim();
						name = st.nextToken().trim();
						surname = st.nextToken().trim();
						gender = st.nextToken().trim();
						locDate = st.nextToken().trim();
						points = st.nextToken().trim();
					}
					
					Gender gen;
					if(gender.equals("Male")) gen = Gender.Male;
					else if(gender.equals("Female")) gen = Gender.Female;
					else gen = Gender.Alien;
					
					CustomerType customerType;
					if(Double.parseDouble(points) < 1500) customerType = CustomerType.Bronze;
					else if(Double.parseDouble(points) < 3000) customerType = CustomerType.Silver;
					else customerType = CustomerType.Gold;
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
					Date date = formatter.parse(locDate);
					Customer customer = new Customer(username, password, name, surname, gen, date);
					customersInner.put(customer.getUsername(),customer);
					customer.setCustomerType(customerType);
					customer.setPoints(Double.parseDouble(points));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		return customersInner;
	}
}
