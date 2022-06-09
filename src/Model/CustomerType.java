package Model;

public class CustomerType {

	private String type;
	private int discount;
	private int requiredPoints;
	
	public CustomerType() {
		super();
	}

	public CustomerType(String type, int discount, int requiredPoints) {
		this();
		this.type = type;
		this.discount = discount;
		this.requiredPoints = requiredPoints;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	
	
	
	
}
