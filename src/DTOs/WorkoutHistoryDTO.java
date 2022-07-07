package DTOs;

import java.util.Date;

import enums.SportBuildingType;
import enums.WorkoutType;

public class WorkoutHistoryDTO {
	private Date checkinDate;
	private int hours;
	private String workoutName;
	private WorkoutType workoutType;
	private String sportBuildingName;
	private int price;
	private SportBuildingType sportBuildingtype;
	
	public WorkoutHistoryDTO(Date checkinDate, int hours, String workoutName, WorkoutType workoutType,
			String sportBuildingName, int price, SportBuildingType sportBuildingtype) {
		super();
		this.checkinDate = checkinDate;
		this.hours = hours;
		this.workoutName = workoutName;
		this.workoutType = workoutType;
		this.sportBuildingName = sportBuildingName;
		this.price = price;
		this.sportBuildingtype = sportBuildingtype;
	}
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public WorkoutType getWorkoutType() {
		return workoutType;
	}
	public void setWorkoutType(WorkoutType workoutType) {
		this.workoutType = workoutType;
	}
	public String getSportBuildingName() {
		return sportBuildingName;
	}
	public void setSportBuildingName(String sportBuildingName) {
		this.sportBuildingName = sportBuildingName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public SportBuildingType getSportBuildingtype() {
		return sportBuildingtype;
	}
	public void setSportBuildingtype(SportBuildingType sportBuildingtype) {
		this.sportBuildingtype = sportBuildingtype;
	}
}
