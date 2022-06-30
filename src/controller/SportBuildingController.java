package controller;

import static spark.Spark.get;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import services.SportBuildingService;

public class SportBuildingController {
	public static Gson gson;
	private static SportBuildingService sportBuildingService;
	public SportBuildingController() {
		sportBuildingService = new SportBuildingService();
		gson = new GsonBuilder()
		        .create();
	}
	public static void getSportBuildings() {
		get("sportBuilding/getAll", (req, res) -> {
			res.type("application/json");
			return gson.toJson(sportBuildingService.getSportBuildings());
		});
	}
	
	public static void getSportBuildingsNoManager() {
		get("sportBuilding/getAllNoManager", (req, res) -> {
			res.type("application/json");
			return gson.toJson(sportBuildingService.getSportBuildingsNoManager());
		});
	}
}
