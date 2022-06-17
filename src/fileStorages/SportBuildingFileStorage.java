package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
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
				if(sportBuilding.getStatus() == SportBuildingStatus.Open)
					outputString += "Open" + ";";
				else
					outputString += "Closed" + ";";
				outputString += sportBuilding.getLocation().getLatitude() + ";";
				outputString += sportBuilding.getLocation().getLongitude() + ";";
				outputString += sportBuilding.getLocation().getAddress().getStreet() + ";";
				outputString += sportBuilding.getLocation().getAddress().getNumber() + ";";
				outputString += sportBuilding.getLocation().getAddress().getCity() + ";";
				outputString += sportBuilding.getLocation().getAddress().getZipCode() + ";";
				outputString += sportBuilding.getImage() + ";";
				outputString += sportBuilding.getAverageGrade() + ";";
				outputString += sportBuilding.getWorkTime() + ";";
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
			String line, name = "", type = "", status = "",latitude = "",longitude = "", street = "", number = "", city = "", zipCode = "", image = "", averageGrade = "", workTime = "";
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
						status = st.nextToken().trim();
						latitude = st.nextToken().trim();
						longitude = st.nextToken().trim();
						street = st.nextToken().trim();
						number = st.nextToken().trim();
						city = st.nextToken().trim();
						zipCode = st.nextToken().trim();
						image = st.nextToken().trim();
						averageGrade = st.nextToken().trim();
						workTime = st.nextToken().trim();
					}
					SportBuildingType buildingType;
					if(type.equals("Gym")) buildingType = SportBuildingType.Gym;
					else if(type.equals("Pool")) buildingType = SportBuildingType.Pool;
					else if(type.equals("sportCenter")) buildingType = SportBuildingType.sportCenter;
					else buildingType = SportBuildingType.danceStudio;
					SportBuildingStatus buildingStatus;
					if(status.equals("Open")) buildingStatus = SportBuildingStatus.Open;
					else buildingStatus = SportBuildingStatus.Closed;
					SportBuilding sportBuilding = new SportBuilding(name, buildingType, buildingStatus,
							new Location(Double.parseDouble(longitude),Double.parseDouble(latitude),
									new Address(street, number, city, zipCode)), image,Double.parseDouble(averageGrade), workTime);
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
