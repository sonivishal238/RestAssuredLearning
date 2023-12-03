package helpers;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.type.TypeReference;

import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserResponse;
import utils.ConfigManager;

public class UserServiceHelper {

	// fetch the data from endpoints
	// Perform http operations: Post/put/patch/get/delete

	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");

	public UserServiceHelper() {

		RestAssured.baseURI = BASE_URL;
	}

}
