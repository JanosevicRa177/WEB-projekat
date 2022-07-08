package services;

import java.util.Date;

import fileStorages.MembershipFileSotrage;
import model.Membership;

public class MembershipService {

	
	private static MembershipFileSotrage memebershipFileStorage;
	
	
	public MembershipService() {
		memebershipFileStorage = new MembershipFileSotrage();
	}
	
	public void createMembership(String memb,int price,String customer,Date today) {
		Membership mem = new Membership();
		//...
	}
}
