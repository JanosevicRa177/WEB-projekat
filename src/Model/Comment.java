package Model;

public class Comment {
	private Customer customer;
	private SportBuilding sportBuilding;
	private String comment;
	private int grade;
	
	public Comment() {
		super();
	}
	public Comment(Customer customer, SportBuilding sportBuilding, String comment, int grade) {
		super();
		this.customer = customer;
		this.sportBuilding = sportBuilding;
		this.comment = comment;
		this.grade = grade;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SportBuilding getSportBuilding() {
		return sportBuilding;
	}
	public void setSportBuilding(SportBuilding sportBuilding) {
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
