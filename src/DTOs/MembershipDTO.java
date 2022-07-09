package DTOs;

import java.util.Date;

public class MembershipDTO {
	
	private String membership;
	private Date expires;
	
	MembershipDTO() {
		
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}
	
	
}
