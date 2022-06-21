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

public class CoachFileStorage {
	
	public Map<String, Coach> coaches;
	
	public CoachFileStorage() {
	}
	
	public Boolean isUniqueUsername(String username) {
		coaches = readCoaches();
		Coach coach = coaches.get(username);
		if(coach == null) return true;
		return false;
	}
	
	public Boolean changeUser(User us) {
		coaches = readCoaches();
		Coach cus = coaches.get(us.getUsername());
		coaches.remove(us.getUsername());
		coaches.put(us.getUsername(), cus.change(us));
		this.writeCoaches();
		return true;
	}
	
	public boolean writeCoaches() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("coaches.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : coaches.keySet())
			{
				Coach customer = coaches.get(key);
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
	
	public Map<String, Coach> readCoaches() {
		Map<String, Coach> coachesInner = new TreeMap<String, Coach>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("./coaches.txt");
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
					Coach Coach = new Coach(username, password, name, surname, gen, date);
					coachesInner.put(Coach.getUsername(),Coach);
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
		return coachesInner;
	}
}
