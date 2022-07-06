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
import model.Manager;
import model.User;

public class ManagerFileStorage {
	
	public Map<String, Manager> managers;
	
	public ManagerFileStorage() {
	}
	
	public Boolean isUniqueUsername(String username) {
		managers = readManagers();
		Manager manager = managers.get(username);
		if(manager == null) return true;
		return false;
	}
	
	public String addManager(Manager manager) {
		managers = readManagers();
		managers.put(manager.getUsername(),manager);
		writeManagers();
		return "Manager registered successfuly!";
	}
	
	public Manager getManager(String username) {
		return this.readManagers().get(username);
	}
	
	public void setSportBuilding(String manager,String sportBuilding) {
		managers = readManagers();
		managers.get(manager).setSportBuilding(sportBuilding);
		writeManagers();
	}
	
	public String CheckSportBuilding(String managerUsername) {
		managers = readManagers();
		if(!managers.get(managerUsername).getSportBuilding().equals("None"))
			return "True";
		return "False";
	}
	
	public Boolean changeUser(User user) {
		managers = readManagers();
		Manager manager = managers.get(user.getUsername());
		managers.remove(user.getUsername());
		managers.put(user.getUsername(), manager.change(user));
		this.writeManagers();
		return true;
	}
	
	public boolean writeManagers() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("managers.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : managers.keySet())
			{
				Manager manager = managers.get(key);
				String outputString = "";
				outputString += manager.getUsername() + ";";
				outputString += manager.getPassword() + ";";
				outputString += manager.getName() + ";";
				outputString += manager.getSurname() + ";";
				if(manager.getGender() == Gender.Male)
				outputString += "Male" + ";";
				else if(manager.getGender() == Gender.Female)
				outputString += "Female" + ";";
				else
				outputString += "Alien" + ";";
				outputString += formatter.format(manager.getBirthDate()) + ";";
				outputString += manager.getSportBuilding();
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, Manager> readManagers() {
		Map<String, Manager> managersInner = new TreeMap<String, Manager>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("./managers.txt");
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "", name = "",surname = "",gender = "", locDate = "", sportBuilding = "";
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
						sportBuilding = st.nextToken().trim();
					}
					Gender gen;
					if(gender.equals("Male")) gen = Gender.Male;
					else if(gender.equals("Female")) gen = Gender.Female;
					else gen = Gender.Alien;
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
					Date date = formatter.parse(locDate);
					Manager Manager = new Manager(username, password, name, surname, gen, date,sportBuilding);
					managersInner.put(Manager.getUsername(),Manager);
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
		return managersInner;
	}
}
