package main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		port(8090);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		get("/test", (req, res) -> {
			return "VEC RADI LAAL xd";
		});

	}

}
