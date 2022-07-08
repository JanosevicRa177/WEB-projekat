package controller;

import static spark.Spark.post;

import java.util.Date;

import model.User;
import services.MembershipService;
import spark.Session;

public class MembershipController {

	private static MembershipService membershipService;
	
	public MembershipController() {
		membershipService = new MembershipService();
	}
	
	public static void createMembership() {
		post("membership/createMembership",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			System.out.println(new Date());
			membershipService.createMembership(req.queryParams("membership"),Integer.parseInt(req.queryParams("price")),user.getUsername(),new Date());
			return "success";
		});
	}
	
}
