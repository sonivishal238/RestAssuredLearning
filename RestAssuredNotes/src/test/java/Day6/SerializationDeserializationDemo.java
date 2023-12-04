package Day6;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Day2.UserPojo;
import io.restassured.http.ContentType;

public class SerializationDeserializationDemo {

	@Test
	public void converPojoToJSON() throws JsonProcessingException {

		// Created Java object using POJO class
		UserPojo contract = new UserPojo();
		contract.setName("Some Automation name");
		contract.setJob("SDET2");

		
		// Convert java object to JSON object --> Serialization
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contract);
		System.out.println(jsonData);
		
		given().contentType(ContentType.JSON).body(jsonData) // convert to string before sending

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).log().all(); // to log the response on the data
	}
	
	@Test
	public void convertJsonToPojo() throws FileNotFoundException, JsonMappingException, JsonProcessingException {
		// Get the file
		File file = new File(".\\src\\test\\java\\Day2\\UserRequest.json");
		
		// read the file
		FileReader fr = new FileReader(file);
		
		// Get the json tokener
		JSONTokener jt = new JSONTokener(fr);
		
		// Create json object from the tokener
		String data = new JSONObject(jt).toString();

		// string to hash map Jackson databind
		ObjectMapper mapper = new ObjectMapper();
	    UserPojo user = mapper.readValue(data, UserPojo.class);
	    
	    System.out.println(user.getName());
	    System.out.println(user.getJob());
	}

}
