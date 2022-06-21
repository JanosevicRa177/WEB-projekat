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

import enums.Gender;
import model.Admin;
import model.Coach;
import model.Manager;
import model.User;

public class AdminFileStorage {
	
	public Map<String, Admin> admins;
	
	public AdminFileStorage() {
	}
	
	public Boolean isUniqueUsername(String username) {
		admins = readAdmins();
		Admin admin = admins.get(username);
		if(admin == null) return true;
		return false;
	}
	
	public Boolean changeUser(User us) {
		admins = readAdmins();
		Admin cus = admins.get(us.getUsername());
		admins.remove(us.getUsername());
		admins.put(us.getUsername(), cus.change(us));
		this.writeAdmins();
		return true;
	}
	
	public boolean writeAdmins() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("admins.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : admins.keySet())
			{
				Admin customer = admins.get(key);
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
				outputString += formatter.format(customer.getBirthDate());
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, Admin> readAdmins() {
		Map<String, Admin> adminsInner = new TreeMap<String, Admin>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("./admins.txt");
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
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
					Date date = formatter.parse(locDate);
					Admin Admin = new Admin(username, password, name, surname, gen, date);
					adminsInner.put(Admin.getUsername(),Admin);
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
		return adminsInner;
	}
}
