package model;

import java.awt.Image;

import enums.WorkoutType;

public class Workout {
	private String name;
	private WorkoutType type;
	private SportBuilding sportBuilding;
	private int duration; // u minutima
	private Coach coach;
	private String description;
	private Image image;
	
	public Workout() {
		super();
	}
	public Workout(String name, WorkoutType type, SportBuilding sportBuilding, int duration, Coach coach,
			String description, Image image) {
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
	public SportBuilding getSportBuilding() {
		return sportBuilding;
	}
	public void setSportBuilding(SportBuilding sportBuilding) {
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
