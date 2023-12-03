package tests;

// using a static import here
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import contracts.Employee;
import io.restassured.http.ContentType;

public class GetAndPostExamples {

	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";

		given().get("/users?page=2").then().statusCode(200).body("data[4].first_name", equalTo("George"))
				.body("data.first_name", hasItems("George", "Rachel"));
	}

	@Test
	public void testPost() {
//		Map<String, String> map = new HashMap<>();
//		map.put("name", "Vishal");
//		map.put("job","sdet");

		JSONObject request = new JSONObject();
		request.put("name", "Vishal");
		request.put("job", "sdet");
		System.out.println(request.toJSONString());

		baseURI = "https://reqres.in/api";
		given().header("content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().post("/users").then().statusCode(201).log().all();
	}
	
	@Test
	public void postUsingPojo()
	{
		Employee emp = new Employee();
		emp.setName("Vishal");
		emp.setJob("sdet");
		
		baseURI = "https://reqres.in/api";
		
		given()
			.contentType("application/json")
			.body(emp)
			
		.when()
			.post("/users")
			
		.then()
			.statusCode(201).log().all();
	}
}
