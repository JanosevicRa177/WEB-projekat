package services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import enums.MembershipStatus;
import enums.MembershipType;
import fileStorages.MembershipFileSotrage;
import model.Membership;

public class MembershipService {

	
	private static MembershipFileSotrage memebershipFileStorage;
	
	
	public MembershipService() {
		memebershipFileStorage = new MembershipFileSotrage();
	}
	
	public void checkMembership(String customer) {
		memebershipFileStorage.checkMembership(customer);
	}
	
	public Membership getMembership(String username) {
		for(Membership mem : memebershipFileStorage.readMemberships().values()) {
			if(mem.getCustomer().equals(username)) {
				if(mem.getStatus() == MembershipStatus.Inactive) return null;
				return mem;
			}
		}
		return null;
	}
	
	public void createMembership(String memb,int price,String customer,Date today) {
		LocalDate locD;
		int WN;
		MembershipType type;
		if(memb.equals("cheap")) {
			type = MembershipType.Monthly;
			locD = LocalDate.now().plusDays(30);
			WN = 5;
		}
		else if(memb.equals("expensive")) {
			type = MembershipType.Monthly;
			locD = LocalDate.now().plusDays(30);
			WN = 15;
		}
		else {
			type = MembershipType.Yearly;
			locD = LocalDate.now().plusDays(365);
			WN = 200;
		}
		Date date = Date.from(locD.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Membership mem = new Membership(memb,type,today,date,price,customer,MembershipStatus.Active,WN);
		memebershipFileStorage.createMembership(mem);
	}
}
