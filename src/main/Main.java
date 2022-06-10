package main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import model.User;

public class Main {
	
	private static Gson g = new Gson();

	public static void main(String[] args) throws IOException {
		port(8090);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("/test1", (req, res) -> {
			System.out.println("xd");
			return "radi xd";
		});
		
		post("/test", (req, res) -> {
			String payload = req.body();
			User u = g.fromJson(payload, User.class);
			return u.getName();
		});

	}

}
