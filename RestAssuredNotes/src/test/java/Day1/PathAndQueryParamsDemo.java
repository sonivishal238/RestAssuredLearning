package Day1;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class PathAndQueryParamsDemo {

	@Test
	public void testQueryAnddPath() {

		// https://reqres.in/api/users?page=2&id=5
		
		given()
			.pathParam("usersPath", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		
		.when()
			.get("https://reqres.in/api/{usersPath}")
		
		.then()
			.statusCode(200)
			.log().all();
	}
}
