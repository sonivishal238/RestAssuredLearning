package Day5;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FileUploadDownloadDemo {

	@Test
	public void uploadTest() {

		String path = System.getProperty("user.dir") + "\\src\\test\\java\\Day5\\testData.txt";
		System.out.println("Path is:" + path);
		File file = new File(path);

		Response response = 
				given()
					.contentType(ContentType.MULTIPART)
					.multiPart("file", file)
				
			    .when()
					.post("https://the-internet.herokuapp.com/upload")
					
				.thenReturn();

		System.out.println(response.asPrettyString());
	}
}
