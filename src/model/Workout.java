package model;
import enums.WorkoutType;

public class Workout {
	private String name;
	private WorkoutType type;
	private String sportBuildingName;
	private String duration; // in minutes
	private String coachUsername;
	private String description;
	private String image;
	private int price;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Workout() {
		super();
	}
	public Workout(String name, WorkoutType type, String sportBuildingName, String duration, String coachUsername,
			String description, String image,int price) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.sportBuildingName = sportBuildingName;
		this.duration = duration;
		this.coachUsername = coachUsername;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCoachUsername() {
		return coachUsername;
	}
	public void setCoachUsername(String coachUsername) {
		this.coachUsername = coachUsername;
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
