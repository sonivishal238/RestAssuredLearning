package tests;

//using a static import here
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchAndDeleteTest {

	@Test
	public void testPut() {
//		Map<String, String> map = new HashMap<>();
//		map.put("name", "Vishal");
//		map.put("job","sdet");

		JSONObject request = new JSONObject();
		request.put("name", "Vishal");
		request.put("job", "sdet");
		System.out.println(request.toJSONString());

		baseURI = "https://reqres.in/api";
		given().header("content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().put("/users/2").then().statusCode(200).log().all();
	}

	@Test
	public void testPatch() {
//		Map<String, String> map = new HashMap<>();
//		map.put("name", "Vishal");
//		map.put("job","sdet");

		JSONObject request = new JSONObject();
		request.put("name", "Vishal");
		request.put("job", "sdet");
		System.out.println(request.toJSONString());

		baseURI = "https://reqres.in/";
		given().header("content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().patch("api/users/2").then().statusCode(200).log().all();
	}

	@Test
	public void testDelete() {

		baseURI = "https://reqres.in/";
		when().delete("api/users/2").then().statusCode(204).log().all();
	}
}
