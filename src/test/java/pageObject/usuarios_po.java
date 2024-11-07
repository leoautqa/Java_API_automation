package pageObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;

import comum.common_steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class usuarios_po {
	
	public static Response response;

	private Faker faker = new Faker();	
	private String name = faker.name().fullName();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();

    @When("Get list users")
    public void get_list_users() {
    	Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios");
    	common_steps.setResponse(response);
    }
	
    
    
}


