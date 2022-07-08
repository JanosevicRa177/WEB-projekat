package model;

import java.util.Date;

import enums.MembershipStatus;
import enums.MembershipType;

public class Membership {
	private String id;
	private MembershipType type;
	private Date paymentDate;
	private Date expires;
	private int price;
	private String customer;
	private MembershipStatus status;
	private int workoutNumber;
	
	public Membership() {
		super();
	}
	public Membership(String id, MembershipType type, Date paymentDate, Date expires, int price,String customer,MembershipStatus status
			, int workoutNumber) {
		super();
		this.id = id;
		this.type = type;
		this.paymentDate = paymentDate;
		this.expires = expires;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.workoutNumber = workoutNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MembershipType getType() {
		return type;
	}
	public void setType(MembershipType type) {
		this.type = type;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public MembershipStatus getStatus() {
		return status;
	}
	public void setStatus(MembershipStatus status) {
		this.status = status;
	}
	public int getWorkoutNumber() {
		return workoutNumber;
	}
	public void setWorkoutNumber(int workoutNumber) {
		this.workoutNumber = workoutNumber;
	}
	
	
	
	
}
