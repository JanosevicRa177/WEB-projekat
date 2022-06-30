package model;

import enums.SportBuildingStatus;
import enums.SportBuildingType;

public class SportBuilding {
	private String name;
	private SportBuildingType type;
	private SportBuildingStatus status;
	private Location location;
	private String image;
	private double averageGrade;
	private String workTime;
	private String manager;
	
	public SportBuilding() {
		super();
	}
	public SportBuilding(String name, SportBuildingType type, SportBuildingStatus status, Location location,
			String image, double averageGrade, String workTime) {
		super();
		this.name = name;
		this.type = type;
		this.status = status;
		this.location = location;
		this.image = image;
		this.averageGrade = averageGrade;
		this.workTime = workTime;
		this.manager = "none";
	}
	
	public SportBuilding(String name, SportBuildingType type, SportBuildingStatus status, Location location,
			String image, double averageGrade, String workTime,String manager) {
		super();
		this.name = name;
		this.type = type;
		this.status = status;
		this.location = location;
		this.image = image;
		this.averageGrade = averageGrade;
		this.workTime = workTime;
		this.manager = manager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SportBuildingType getType() {
		return type;
	}
	public void setType(SportBuildingType type) {
		this.type = type;
	}
	public SportBuildingStatus getStatus() {
		return status;
	}
	public void setStatus(SportBuildingStatus status) {
		this.status = status;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
}
