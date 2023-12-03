package Day4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseDemo {
	
	@Test
	public void testJSONResponse() {

		// Approach 1
		given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("https://reqres.in/api/users/2")
		
		.then()
			.statusCode(200)
			.header("content-type", "application/json; charset=utf-8")
			.body("data.email", equalTo("janet.weaver@reqres.in"));
		
		// Approach 2 storing the response in a variable
		Response res = given().contentType("ContentType.json")

				.when().get("https://reqres.in/api/users/2");
		
		String emailResponse = res.jsonPath().get("data.email");
		Assert.assertEquals(emailResponse, "janet.weaver@reqres.in");
	}
	
	@Test
	public void testJSONObJectData() {
		
		Response res = given().contentType(ContentType.JSON)

				.when().get("https://reqres.in/api/users?page=2");
		
		JSONObject jo = new JSONObject(res.asString()); // Converting response to json Object type
		System.out.println("First names are: ");
		
		for(int i = 0; i <jo.getJSONArray("data").length(); i++) {
			
			String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			System.out.println(firstName);
		}
	}
}
