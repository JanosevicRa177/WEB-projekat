package model;

import java.time.LocalDate;

import enums.MembershipStatus;
import enums.MembershipType;

public class Membership {
	private String id;
	private MembershipType type;
	private LocalDate paymentDate;
	private LocalDate expires;
	private int price;
	private Customer customer;
	private MembershipStatus status;
	private int workoutNumber;
	
	public Membership() {
		super();
	}
	public Membership(String id, MembershipType type, LocalDate paymentDate, LocalDate expires, int price,Customer customer,MembershipStatus status
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
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public LocalDate getExpires() {
		return expires;
	}
	public void setExpires(LocalDate expires) {
		this.expires = expires;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
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
