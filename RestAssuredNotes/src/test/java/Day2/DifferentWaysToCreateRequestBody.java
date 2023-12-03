package Day2;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DifferentWaysToCreateRequestBody {

	/*
	 * 1. Hashmap => already done in BasicCrudDemo.java
	 * 2. using org.json
	 * 3. using Pojo
	 * 4. using external json file
	 */
	
	 // 2. using org.json
	@Test
	public void postUsingOrgJsonLibrary() {
		
		// Very similar to HashMap
		JSONObject data = new JSONObject();
		
		data.put("name", "Vishal");
		data.put("job", "sdet");
		
		given()
			.contentType(ContentType.JSON)
			.body(data.toString()) // convert to string before sending
			
		.when()
			.post("https://reqres.in/api/users")
		
		.then()
			.statusCode(201)
			.log().all(); // to log the response on the data
	}
	
	
	//3. using Pojo
	@Test
	public void postUsingPOJOClass() {

		UserPojo contract = new UserPojo();
		contract.setName("Some Automation name");
		contract.setJob("SDET2");
		
		given()
			.contentType(ContentType.JSON)
			.body(contract) // convert to string before sending
			
		.when()
			.post("https://reqres.in/api/users")
		
		.then()
			.statusCode(201)
			.log().all(); // to log the response on the data
	}
	
	//4. Uusing external json file
	@Test
	public void postUsingExternalJSONFile() throws FileNotFoundException {

		// Get the file
		File file = new File(".\\src\\test\\java\\Day2\\UserRequest.json");
		
		// read the file
		FileReader fr = new FileReader(file);
		
		// Get the json tokener
		JSONTokener jt = new JSONTokener(fr);
		
		// Create json object from the tokener
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType(ContentType.JSON)
			.body(data.toString()) // convert to string before sending
			
		.when()
			.post("https://reqres.in/api/users")
		
		.then()
			.statusCode(201)
			.log().all(); // to log the response on the data
	}
	
}
