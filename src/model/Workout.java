package model;
import enums.WorkoutType;

public class Workout {
	private String name;
	private WorkoutType type;
	private String sportBuilding;
	private int duration; // u minutima
	private Coach coach;
	private String description;
	private String image;
	
	public Workout() {
		super();
	}
	public Workout(String name, WorkoutType type, String sportBuilding, int duration, Coach coach,
			String description, String image) {
		super();
		this.name = name;
		this.type = type;
		this.sportBuilding = sportBuilding;
		this.duration = duration;
		this.coach = coach;
		this.description = description;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WorkoutType getType() {
		return type;
	}
	public void setType(WorkoutType type) {
		this.type = type;
	}
	public String getSportBuilding() {
		return sportBuilding;
	}
	public void setSportBuilding(String sportBuilding) {
		this.sportBuilding = sportBuilding;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
