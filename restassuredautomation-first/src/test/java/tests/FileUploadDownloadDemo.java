package tests;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FileUploadDownloadDemo {

	@Test
	public void uploadTest() {
		
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\data\\testData.txt";
		System.out.println("Path is:" + path);
		File file = new File(path);
		
		Response response = RestAssured
								.given()
								.multiPart(file)
								.post("https://the-internet.herokuapp.com/upload")
								.thenReturn();
		
		System.out.println(response.asPrettyString());
	}
}
