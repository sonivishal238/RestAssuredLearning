package Day7;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
public class AuthorizationDemo {

	/*
	 * Basic 
	 * Digest 
	 * Preempive 
	 * Bearer token 
	 * oauth 1.0, 2.0 
	 * API key based
	 */
	
	
	@Test(priority = 1)
	void testBasicAuth() {
		given()
			.auth().basic("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
			
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true)).log().all();
	}
	
	@Test(priority = 2)
	void testDigestAuth() {
		given()
			.auth().digest("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
			
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true)).log().all();
	}
	
	/*
	 * @Test(priority = 3) void testPreemptiveAuth() { given() .auth().preemptive()
	 * 
	 * .when() .get("https://postman-echo.com/basic-auth")
	 * 
	 * .then() .statusCode(200) .body("authenticated", equalTo(true)).log().all(); }
	 */
}
