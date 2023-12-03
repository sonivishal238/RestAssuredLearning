package Day6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Day2.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SerializationDeserializationDemo {

	@Test
	public void convertJSONtoPojo() {

		// Created Java object using POJO class
		UserPojo contract = new UserPojo();
		contract.setName("Some Automation name");
		contract.setJob("SDET2");

		
		// Convert java object to JSON object --> Serialization
		given().contentType(ContentType.JSON).body(contract) // convert to string before sending

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).log().all(); // to log the response on the data
	}

}
