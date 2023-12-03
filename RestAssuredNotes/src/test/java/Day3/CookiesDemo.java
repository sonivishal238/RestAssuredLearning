package Day3;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	@Test
	public void testCookies() {

		given()
		
		.when()
			.get("https://www.google.com")
		
		.then()
		.cookie("AEC") // these cookies may change, and the test will fail in that case
			.log().all();
	}

	
	@Test
	public void testCookiesInfo() {

		Response res = given().when()
			.get("https://www.google.com");
		
		// get single cookies info
		String value1 = res.getCookie("AEC");
		System.out.println("Value of cookie "+"AEC is "+value1);
		
		// get all cookies infor
		Map<String, String> map = res.getCookies();
		for(String cookie : map.keySet()) {
			System.out.println("Cookie key: "+cookie + ", Cookie Value: " + map.get(cookie));
		}
	}
}
