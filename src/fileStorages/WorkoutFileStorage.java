package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import enums.WorkoutType;
import model.Workout;

public class WorkoutFileStorage {
	public Map<String, Workout> workouts;
	
	public WorkoutFileStorage() {
	}
	
	public Boolean isUniqueName(String name) {
		workouts = readWorkouts();
		if(workouts.get(name) == null) return true;
		return false;
	}
	
	public String addWorkout(Workout workout) {
		workouts = readWorkouts();
		workouts.put(workout.getName(),workout);
		writeWorkouts();
		return "Workout registered successfuly!";
	}
	
	public boolean writeWorkouts() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("workouts.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			for(String key : workouts.keySet())
			{
				Workout workout = workouts.get(key);
				String outputString = "";
				outputString += workout.getName() + ";";
				outputString += workout.getSportBuildingName() + ";";
				outputString += workout.getCoachUsername() + ";";
				outputString += workout.getImage() + ";";
				outputString += workout.getDescription() + ";";
				outputString += workout.getDuration() + ";";
				if(workout.getType() == WorkoutType.Group)
				outputString += "Group" + ";";
				else if(workout.getType() == WorkoutType.Gym)
				outputString += "Gym" + ";";
				else if(workout.getType() == WorkoutType.Personal)
				outputString += "Personal" + ";";
				else
				outputString += "Sauna" + ";";
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, Workout> readWorkouts() {
		Map<String, Workout> workoutsInner = new TreeMap<String, Workout>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("workouts.txt");
			in = new BufferedReader(new FileReader(file));
			String line, name = "", type = "", sportBuildingName = "",duration = "",coachUsername = "", description = "", image = "";
			StringTokenizer st;
			try {
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						name = st.nextToken().trim();
						sportBuildingName = st.nextToken().trim();
						coachUsername = st.nextToken().trim();
						image = st.nextToken().trim();
						description = st.nextToken().trim();
						duration = st.nextToken().trim();
						type = st.nextToken().trim();
					}
					WorkoutType workoutType;
					if(type.equals("Group")) workoutType = WorkoutType.Group;
					else if(type.equals("Gym")) workoutType = WorkoutType.Gym;
					else if(type.equals("Personal")) workoutType = WorkoutType.Personal;
					else workoutType = WorkoutType.Sauna;
					Workout workout = new Workout(name, workoutType, sportBuildingName, duration, coachUsername, description, image);
					workoutsInner.put(workout.getName(),workout);
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
		return workoutsInner;
	}
}
