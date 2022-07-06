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

import model.ActiveGroupWorkout;
import model.Workout;

public class ActiveGroupWorkoutFileStorage {
	public Map<String, ActiveGroupWorkout> activeGroupWorkouts;
	
	public ActiveGroupWorkoutFileStorage() {
	}
	
	public String AddActiveGroupWorkout(Date date,int hours,Workout workout) {
		activeGroupWorkouts = readActiveGroupWorkouts();
		ActiveGroupWorkout activeGroupWorkout = new ActiveGroupWorkout(date,hours,workout.getName(),activeGroupWorkouts.size());
		activeGroupWorkouts.put(Integer.toString(activeGroupWorkout.getId()),activeGroupWorkout);
		writeActiveGroupWorkouts();
		return "Success";
	}
	
	public boolean writeActiveGroupWorkouts() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("ActiveGroupWorkouts.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : activeGroupWorkouts.keySet())
			{
				ActiveGroupWorkout activeGroupWorkout = activeGroupWorkouts.get(key);
				String outputString = "";
				outputString += activeGroupWorkout.getId() + ";";
				outputString += activeGroupWorkout.getWorkout() + ";";
				outputString += formatter.format(activeGroupWorkout.getCheckinDate()) + ";";
				outputString += activeGroupWorkout.getHours() + ";";
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, ActiveGroupWorkout> readActiveGroupWorkouts() {
		Map<String, ActiveGroupWorkout> activeGroupWorkoutsInner = new TreeMap<String, ActiveGroupWorkout>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("ActiveGroupWorkouts.txt");
			in = new BufferedReader(new FileReader(file));
			String line,id = "", workoutName = "",dateString = "",hours = "";
			StringTokenizer st;
			try {
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						id = st.nextToken().trim();
						workoutName = st.nextToken().trim();
						dateString = st.nextToken().trim();
						hours = st.nextToken().trim();
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
					Date date = formatter.parse(dateString);
					ActiveGroupWorkout activeGroupWorkout = new ActiveGroupWorkout(date,Integer.parseInt(hours),workoutName,Integer.parseInt(id));
					activeGroupWorkoutsInner.put(id,activeGroupWorkout);
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
		return activeGroupWorkoutsInner;
	}
}
