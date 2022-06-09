package model;

import java.awt.Image;

import enums.SportBuildingStatus;
import enums.SportBuildingType;

public class SportBuilding {
	private String name;
	private SportBuildingType type;
	private SportBuildingStatus status;
	private Location location;
	private Image image;
	private double averageGrade;
	private String workTime;
	
	public SportBuilding() {
		super();
	}
	public SportBuilding(String name, SportBuildingType type, SportBuildingStatus status, Location location,
			Image image, double averageGrade, String workTime) {
		super();
		this.name = name;
		this.type = type;
		this.status = status;
		this.location = location;
		this.image = image;
		this.averageGrade = averageGrade;
		this.workTime = workTime;
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
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
	
	
}
