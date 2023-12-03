package Day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class HeaderDemo {
	
	@Test
	public void testHeaders() {

		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
		.header("content-type", "text/html; charset=ISO-8859-1")
		.and()
		.header("Referrer-Policy", "null");
	}

}
