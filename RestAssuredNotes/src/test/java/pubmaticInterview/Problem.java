package pubmaticInterview;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
	

public class Problem{
	@Test
	public void Test() {
		
		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		result.put("DIRECT", 0);
		result.put("RESELLER", 0);
		
		Response response = given()
		
		.when()
		
			.get("https://timesofindia.indiatimes.com/ads.txt");
		

		System.out.println(Arrays.stream(response.asString().split("\n")).filter(x -> x.contains("pubmatic.com") && x.contains("DIRECT")).count());
		System.out.println(Arrays.stream(response.asString().split("\n")).filter(x -> x.contains("pubmatic.com") && x.contains("RESELLER")).count());
		
		
	}
}
