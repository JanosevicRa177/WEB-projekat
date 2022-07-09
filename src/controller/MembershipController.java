package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.User;
import services.MembershipService;
import spark.Session;

public class MembershipController {

	private static MembershipService membershipService;
	public static Gson gson;
	
	public MembershipController() {
		membershipService = new MembershipService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void createMembership() {
		post("membership/createMembership",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			membershipService.createMembership(req.queryParams("membership"),Integer.parseInt(req.queryParams("price")),user.getUsername(),new Date());
			return "success";
		});
	}
	
	public static void getMemebership() {
			get("membership/getMembership", (req, res) -> {
				res.type("application/json");
				Session ss = req.session(true);
				User user = ss.attribute("user");
				return gson.toJson(membershipService.getMembership(user.getUsername()));
			});
	}

}
