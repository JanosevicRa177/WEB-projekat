package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import model.Workout;
import model.WorkoutHistory;

public class WorkoutHistoryFileStorage {
	public Map<String, WorkoutHistory> workoutsHistories;
	
	public WorkoutHistoryFileStorage() {
	}
	
	public String AddWorkoutHistory(Date date,int hours,Workout workout,String customerUsername) {
		workoutsHistories = readWorkoutsHistories();
		WorkoutHistory workoutHistory = new WorkoutHistory(date,workout.getName(),customerUsername,workout.getCoachUsername(),workoutsHistories.size(),hours);
		workoutsHistories.put(Integer.toString(workoutHistory.getId()),workoutHistory);
		writeWorkoutsHistories();
		return "Success";
	}
	
	public Collection<WorkoutHistory> getAllWorkoutHistories(){
		return this.readWorkoutsHistories().values();
	}
	
	public Collection<WorkoutHistory> GetWorkoutHistoryByCustomer(String customer) {
		workoutsHistories = readWorkoutsHistories();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date dateTodayMinusMonth = Date.from(LocalDate.now().plusMonths(-1).atStartOfDay(defaultZoneId).toInstant());
		Collection<WorkoutHistory> customerWorkoutHistory = new HashSet<WorkoutHistory>();
		for(WorkoutHistory workout : workoutsHistories.values()) {
			if(workout.getCustomer().equals(customer) && (workout.getCheckinDate().compareTo(dateTodayMinusMonth) > 0)) {
				customerWorkoutHistory.add(workout);
			}
		}
		return customerWorkoutHistory;
	}
	
	public boolean writeWorkoutsHistories() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("workoutsHistories.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : workoutsHistories.keySet())
			{
				WorkoutHistory workoutHistory = workoutsHistories.get(key);
				String outputString = "";
				outputString += workoutHistory.getId() + ";";
				outputString += workoutHistory.getWorkout() + ";";
				outputString += workoutHistory.getCoach() + ";";
				outputString += workoutHistory.getCustomer() + ";";
				outputString += formatter.format(workoutHistory.getCheckinDate()) + ";";
				outputString += workoutHistory.getHours() + ";";
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, WorkoutHistory> readWorkoutsHistories() {
		Map<String, WorkoutHistory> workoutsHistoryInner = new TreeMap<String, WorkoutHistory>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("workoutsHistories.txt");
			in = new BufferedReader(new FileReader(file));
			String line,id = "", workoutName = "", coach = "", customer = "",dateString = "",hours = "";
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
						coach = st.nextToken().trim();
						customer = st.nextToken().trim();
						dateString = st.nextToken().trim();
						hours = st.nextToken().trim();
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
					Date date = formatter.parse(dateString);
					WorkoutHistory workoutHistory = new WorkoutHistory(date,workoutName,customer,coach,Integer.parseInt(id),Integer.parseInt(hours));
					workoutsHistoryInner.put(id,workoutHistory);
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
		return workoutsHistoryInner;
	}
}
