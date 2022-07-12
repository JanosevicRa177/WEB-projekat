package model;

import enums.CommentStatus;

public class Comment {
	private int id;
	private String customer;
	private String sportBuilding;
	private String comment;
	private int grade;
	private CommentStatus status;
	
	
	public Comment() {
		super();
	}
	public Comment(String customer, String sportBuilding, String comment, int grade, int id,CommentStatus status) {
		super();
		this.customer = customer;
		this.sportBuilding = sportBuilding;
		this.comment = comment;
		this.grade = grade;
		this.id = id;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CommentStatus getStatus() {
		return status;
	}
	public void setStatus(CommentStatus status) {
		this.status = status;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getSportBuilding() {
		return sportBuilding;
	}
	public void setSportBuilding(String sportBuilding) {
		this.sportBuilding = sportBuilding;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
