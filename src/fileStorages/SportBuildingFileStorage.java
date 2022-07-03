package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import enums.SportBuildingStatus;
import enums.SportBuildingType;
import model.Address;
import model.Location;
import model.SportBuilding;

public class SportBuildingFileStorage {
	private Map<String, SportBuilding> sportBuildings;
	
	public SportBuildingFileStorage() {
	}
	
	public Collection<SportBuilding> getSportBuildings(){
		sportBuildings = readSportBuildings();
		return sportBuildings.values();
	}
	public SportBuilding GetSportBuilding(String sportBuildingName){
		sportBuildings = readSportBuildings();
		return sportBuildings.get(sportBuildingName);
	}
	
	public Collection<SportBuilding> getSportBuildingsNoManager(){
		Map<String, SportBuilding> map = new TreeMap<String, SportBuilding>(String.CASE_INSENSITIVE_ORDER);
		this.readSportBuildings();
		for(SportBuilding sb : this.readSportBuildings().values()) {
			if(sb.getManager().equals("none")) {
				map.put(sb.getName(), sb);
			}
		}
		return map.values();
	}
	
	public boolean addSportBuilding(SportBuilding spB) {
		sportBuildings = this.readSportBuildings();
		sportBuildings.put(spB.getName(), spB);
		this.writeSportBuildings();
		return true;
	}
	
	public void setManager(String manager,String sportBuilding) {
		sportBuildings = readSportBuildings();
		SportBuilding sp = sportBuildings.get(sportBuilding);
		sp.setManager(manager);
		this.writeSportBuildings();
	}
	
	public boolean isNameUnique(String name) {
		sportBuildings = this.readSportBuildings();
		SportBuilding sp = sportBuildings.get(name);
		if(sp == null) return true;
		return false;
	}
	
	public boolean writeSportBuildings() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("./sportBuildings.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			for(String key : sportBuildings.keySet())
			{
				SportBuilding sportBuilding = sportBuildings.get(key);
				String outputString = "";
				outputString += sportBuilding.getName() + ";";
				
				if(sportBuilding.getType() == SportBuildingType.Pool)
					outputString += "Pool" + ";";
				else if(sportBuilding.getType() == SportBuildingType.Gym)
					outputString += "Gym" + ";";
				else if(sportBuilding.getType() == SportBuildingType.sportCenter)
					outputString += "sportCenter" + ";";
				else
					outputString += "danceStudio" + ";";
				outputString += sportBuilding.getLocation().getLatitude() + ";";
				outputString += sportBuilding.getLocation().getLongitude() + ";";
				outputString += sportBuilding.getLocation().getAddress().getStreet() + ";";
				outputString += sportBuilding.getLocation().getAddress().getNumber() + ";";
				outputString += sportBuilding.getLocation().getAddress().getCity() + ";";
				outputString += sportBuilding.getLocation().getAddress().getZipCode() + ";";
				outputString += sportBuilding.getImage() + ";";
				outputString += sportBuilding.getAverageGrade() + ";";
				if(!(sportBuilding.getWorkTime() == null))
				outputString += sportBuilding.getWorkTime() + ";";
				else outputString += "00:00-00:00;";
				outputString += sportBuilding.getManager() + ";";
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	public Map<String, SportBuilding> readSportBuildings() {
		Map<String, SportBuilding> sportBuildingsInner = new TreeMap<String, SportBuilding>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("./sportBuildings.txt");
			in = new BufferedReader(new FileReader(file));
			String line, name = "", type = "",latitude = "",longitude = "", street = "", number = "", city = "", zipCode = "", image = "", averageGrade = "", workTime = "",manager ="";
			StringTokenizer st;
			try {
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						name = st.nextToken().trim();
						type = st.nextToken().trim();
						latitude = st.nextToken().trim();
						longitude = st.nextToken().trim();
						street = st.nextToken().trim();
						number = st.nextToken().trim();
						city = st.nextToken().trim();
						zipCode = st.nextToken().trim();
						image = st.nextToken().trim();
						averageGrade = st.nextToken().trim();
						workTime = st.nextToken().trim();
						manager = st.nextToken().trim();
					}
					SportBuildingType buildingType;
					if(type.equals("Gym")) buildingType = SportBuildingType.Gym;
					else if(type.equals("Pool")) buildingType = SportBuildingType.Pool;
					else if(type.equals("sportCenter")) buildingType = SportBuildingType.sportCenter;
					else buildingType = SportBuildingType.danceStudio;
					SportBuildingStatus buildingStatus;
					
					String times[] = workTime.split("-");
					String beginTime[] = times[0].split(":");
					String endTime[] = times[1].split(":");
					if(Integer.parseInt(beginTime[0]) == LocalTime.now().getHour()) {
						if(Integer.parseInt(beginTime[1]) < LocalTime.now().getMinute()) buildingStatus = SportBuildingStatus.Open;
						else buildingStatus = SportBuildingStatus.Closed;
					}
					else if(Integer.parseInt(endTime[0]) == LocalTime.now().getHour()) {
						if(Integer.parseInt(endTime[1]) > LocalTime.now().getMinute()) buildingStatus = SportBuildingStatus.Open;
						else buildingStatus = SportBuildingStatus.Closed;
					}
					else if(Integer.parseInt(beginTime[0]) < LocalTime.now().getHour() && Integer.parseInt(endTime[0]) > LocalTime.now().getHour()) {
						buildingStatus = SportBuildingStatus.Open;
					}
					else buildingStatus = SportBuildingStatus.Closed;
					SportBuilding sportBuilding = new SportBuilding(name, buildingType, buildingStatus,
							new Location(Double.parseDouble(longitude),Double.parseDouble(latitude),
									new Address(street, number, city, zipCode)), image,Double.parseDouble(averageGrade), workTime,manager);
					sportBuildingsInner.put(sportBuilding.getName(),sportBuilding);
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
		return sportBuildingsInner;
	}
}
