package Day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

/*
 given()
 	content-type, set cookies, add auth, add path or query param, set headers info etc...
 	
 when()
 	get, post, put, delete, etc etc http methods

 then()
 	Validate status code, extract response, extract headers and cookies & response body, etc etc...
 */

public class BasicCrudDemo {

	int id;
	
	@Test(priority = 1)
	public static void getAllUsers() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void createUser() {
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "Vishal");
		data.put("job", "sdet");
		
		id = given()
			.contentType(ContentType.JSON)
			.body(data)
			
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		System.out.println("unique id: " + id);
//		.then()
//			.statusCode(201)
//			.log().all(); // to log the response on the data
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "Vishal");
		data.put("job", "sse");
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(200)
			.log().all(); // to log the response on the data	
	}
	
	@Test(priority = 4, dependsOnMethods = {"updateUser"})
	void deleteUser() {

		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all(); // to log the response on the data	
	}
}
