package DTOs;

import java.util.Date;

public class ActiveGroupWorkoutDTO {
	private Date checkinDate;
	private int hours;
	private String workout;
	private String sportBuildingName;
	private String duration; // in minutes
	private String coachUsername;
	private String description;
	private String image;
	
	
	public ActiveGroupWorkoutDTO(Date checkinDate, int hours, String workout, String sportBuildingName, String duration,
			String coachUsername, String description, String image, int price) {
		super();
		this.checkinDate = checkinDate;
		this.hours = hours;
		this.workout = workout;
		this.sportBuildingName = sportBuildingName;
		this.duration = duration;
		this.coachUsername = coachUsername;
		this.description = description;
		this.image = image;
		this.price = price;
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
	public String getWorkout() {
		return workout;
	}
	public void setWorkout(String workout) {
		this.workout = workout;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	private int price;
}
