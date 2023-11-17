package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
/*import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.testng.annotations.Test;

import java.io.IOException;*/

public class UserTests {


	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		// Logs
		logger= LogManager.getLogger(this.getClass());
		

	}
	@Test(priority=1)
	public void testPostUser() 
	{
		
		logger.info("************** Creating New User ****************");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************** New User Created Successfully ****************");


	}
}

	/*@Test(priority=2)
	public void testGetUser()
	{
		Response response=UserEndPoints.readUser(this.userPayload.getUserName());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);


	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		// Update Data Using Payload 
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response=UserEndPoints.updateUser(this.userPayload.getUserName(), userPayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Checking Data after Update
		
		Response responseAfterUpdate=UserEndPoints.readUser(this.userPayload.getUserName());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response=UserEndPoints.deleteUser(this.userPayload.getUserName());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}*/


	/*
	@Test(priority=5)
	public void testJsonPathValidation() {
		String jsonResponse = "{ \"name\": \"John\", \"age\": 30, \"city\": \"New York\" }";

		// Parse JSON response
		JsonPath jsonPath = new JsonPath(jsonResponse);

		// Validate using JSON path
		String name = jsonPath.get("name");
		int age = jsonPath.getInt("age");

		// Assertion
		Assert.assertEquals(name, "John", "Name validation failed");
		Assert.assertEquals(age, 30, "Age validation failed");
	}


	@Test(priority=6)
	public void testSchemaValidation() throws IOException, ProcessingException {
		// Load JSON schema
		JsonNode schemaNode = JsonLoader.fromResource("/path/to/schema.json");
		JsonSchema schema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);

		// Load JSON data

		JsonNode dataNode = JsonLoader.fromResource("/path/to/data.json");
		
		//Make sure to replace /path/to/schema.json and /path/to/data.json with the actual paths to your JSON schema and data files. 

		// Assertion
		try {
			schema.validate(dataNode);
		} catch (ProcessingException e) {
			// Assertion failure if an exception is thrown
			throw new AssertionError("Schema validation failed: " + e.getMessage());
			
			//Adjust the assertions based on your specific use case and the expected structure of your JSON data.
		}
	}*/





