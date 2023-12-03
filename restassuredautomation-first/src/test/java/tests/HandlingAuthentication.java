package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HandlingAuthentication {

	Response response = RestAssured
							.given()
							.header("bearer", "token")
							.get("urlhere");
}
