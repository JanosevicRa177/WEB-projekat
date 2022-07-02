package model;
import enums.WorkoutType;

public class Workout {
	private String name;
	private WorkoutType type;
	private String sportBuildingName;
	private int duration; // in minutes
	private String coachName;
	private String description;
	private String image;
	
	public Workout() {
		super();
	}
	public Workout(String name, WorkoutType type, String sportBuildingName, int duration, String coachName,
			String description, String image) {
		super();
		this.name = name;
		this.type = type;
		this.sportBuildingName = sportBuildingName;
		this.duration = duration;
		this.coachName = coachName;
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
	public String getSportBuildingName() {
		return sportBuildingName;
	}
	public void setSportBuildingName(String sportBuildingName) {
		this.sportBuildingName = sportBuildingName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
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
