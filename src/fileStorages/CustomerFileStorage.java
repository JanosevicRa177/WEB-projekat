package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import enums.Gender;
import model.Customer;

public class CustomerFileStorage {
	
	public static ArrayList<Customer> cusList;
	
	public CustomerFileStorage() {
		if(cusList == null) {
			cusList = readCustomers();
		}
		
	}
	
	public ArrayList<Customer> readCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		BufferedReader in = null;
		try {
			File file = new File("./customers.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "", name = "",surname = "",gender = "", locDate = "";
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
					}
					Gender gen;
					if(gender.equals("Male")) gen = Gender.Male;
					else if(gender.equals("Female")) gen = Gender.Female;
					else gen = Gender.Alien;
					Customer customer = new Customer(username,password,name,surname,gen, LocalDate.of(2000,5,15));
					customers.add(customer);
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
		return customers;
	}
	
	public String addCustomer(Customer cus) {
		cusList.add(cus);
		return "SUCCESS";
	}
}
