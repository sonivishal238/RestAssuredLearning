package Day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponseDemo {
	
	@Test
	public void testXMLResponse() { // syntax very similar to JSON Response demo class tests

		// Approach 1
		given()
			.contentType(ContentType.XML)
			
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
			.statusCode(200)
			.header("content-type", "application/xml; charset=utf-8")
			
			// if we only take Travelerinformation then it will return all of the names so we need Travelerinformation[0]
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		
		
		// Approach 2
		
		Response res = given().contentType(ContentType.XML)
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		String name = res.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name");
		Assert.assertEquals(name, "Developer");
	}
	
	@Test
	public void testXMLResponseBody() {
		
		Response res = given().contentType(ContentType.XML)
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlPath = new XmlPath(res.asString());
		
		List<String> information = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
		System.out.println("Total no of travelers: " + information.size());
		
		
		List<String> travellersNames = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		System.out.println("travellers names are: ");
		
		for(String name : travellersNames) {
			System.out.println(name);
		}
	}
}
