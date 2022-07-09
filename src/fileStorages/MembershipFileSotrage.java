package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import enums.MembershipStatus;
import enums.MembershipType;
import model.Membership;
import model.User;
import spark.Session;

public class MembershipFileSotrage {

	
	public Map<String, Membership> memberships;
	
	public MembershipFileSotrage() {
		
	}
	
	public void createMembership(Membership memb) {
		memberships = this.readMemberships();
		if(memberships.get(memb.getCustomer()) == null) {
			memberships.remove(memb.getCustomer());
		}
		memberships.put(memb.getCustomer(), memb);
		this.writeMemberships();
	}
	
	public Membership getCustomersMembership(String customer) {
		for(Membership mem : this.readMemberships().values() ) {
			if(mem.getCustomer().equals(customer)){
				return mem;
			}
		}
		return null;
	}
	
	public void increase(String customer) {
		memberships = this.readMemberships();
		for(Membership mem :  memberships.values()) {
			if(mem.getCustomer().equals(customer)){
				mem.incVisitedSportArena();
			}
		}
		this.writeMemberships();
	}
	
	public boolean checkMembership(String customer) {
		memberships = this.readMemberships();
		Membership memb = memberships.get(customer);
		if(memb.getExpires().before(new Date())) {
			memb.setStatus(MembershipStatus.Inactive);
			this.writeMemberships();
			return false;
		}
		return true;
	}
	
	public boolean writeMemberships() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("memberships.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
			for(String key : memberships.keySet())
			{
				Membership membership = memberships.get(key);
				String outputString = "";
				outputString += membership.getId() + ";";
				if(membership.getType()== MembershipType.Yearly)
					outputString += "Yearly" + ";";
				else
					outputString += "Monthly" + ";";
				outputString += formatter.format(membership.getPaymentDate()) + ";";
				outputString += formatter.format(membership.getExpires()) + ";";
				outputString += membership.getPrice() + ";";
				outputString += membership.getCustomer() + ";";
				if(membership.getStatus() == MembershipStatus.Active)
				outputString += "Active" + ";";
				else
				outputString += "Inactive" + ";";
				outputString += membership.getWorkoutNumber() + ";";
				outputString += membership.getVisitedSportArena() + ";";
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, Membership> readMemberships() {
		Map<String, Membership> coachesInner = new TreeMap<String, Membership>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("./memberships.txt");
			in = new BufferedReader(new FileReader(file));
			String line, id = "", type = "", paymentDate = "",expires = "",price = "", customer = "",status="",workoutNumber="",visitedSportArena="";
			StringTokenizer st;
			try {
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						id = st.nextToken().trim();
						type = st.nextToken().trim();
						paymentDate = st.nextToken().trim();
						expires = st.nextToken().trim();
						price = st.nextToken().trim();
						customer = st.nextToken().trim();
						status = st.nextToken().trim();
						workoutNumber = st.nextToken().trim();
						visitedSportArena = st.nextToken().trim();
					}
					MembershipType memt;
					MembershipStatus memst;
					if(type.equals("Yearly")) memt = MembershipType.Yearly;
					else memt = MembershipType.Monthly;
					if(status.equals("Active")) memst = MembershipStatus.Active;
					else memst = MembershipStatus.Inactive;
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
					Date payDate = formatter.parse(paymentDate);
					Date expDate = formatter.parse(expires);
					Membership membership = new Membership(id,memt,payDate,expDate,Integer.parseInt(price),customer,memst,Integer.parseInt(workoutNumber),Integer.parseInt(visitedSportArena));
					coachesInner.put(membership.getCustomer(),membership);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		return coachesInner;
	}
}
