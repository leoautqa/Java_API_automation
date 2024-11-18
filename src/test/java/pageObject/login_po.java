package pageObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;

import comum.common_steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class login_po {
	
	private Response response;

	private Faker faker = new Faker();	
	private String name = faker.name().fullName();
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();
	
	@Given("User {}")
	public void user(String user) {
		boolean type = false;
		
		if(user == "admin") {
			type = true;
		}
		
		response = given()
                .contentType(ContentType.JSON)
                .body("{ \"nome\": \"" + name + "\","
                	 + " \"email\": \"" + email + "\","
                	 + " \"password\": \"" + password + "\","
                	 + " \"administrador\": \"" + type + "\"}")
                .when()
                .post("/usuarios");
	}
	
    @When("Submit login as {}")
    public void submit_Login(String log) {
    	String logEmail = "";
    	String logPassword = "";
    	
    	if(log.equals("admin") || log.equals("regular")) {
    		logEmail = email;
    		logPassword = password;
    		
    	}else if (log.equals("no registration")) {
    		logEmail = faker.internet().emailAddress();
    		logPassword = faker.internet().password();
    		
    	}else if (log.equals("null value")) {
    		logEmail = null;
    		logPassword = null;
    	}
    	
    	String requestBody = "{ \"email\": " + (logEmail != null ? "\"" + logEmail + "\"" : "null") + ", "
                			+ "\"password\": " + (logPassword != null ? "\"" + logPassword + "\"" : "null") + " }";
    	
        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/login");
        
        common_steps.setResponse(response);
    }
    
    @And("Message {} appear")
    public void message_login_appear(String message) {
    	switch (message) {
	      case "login":
	    	  response.then()
	        	.body("message", equalTo("Login realizado com sucesso"));
	          break;
	      case "invalid":
	    	  response.then()
	        	.body("message", equalTo("Email e/ou senha inválidos"));
	          break;
	      case "of null":
	    	  response.then()
	        	.body("email", equalTo("email deve ser uma string"))
	        	.body("password", equalTo("password deve ser uma string"));
	          break;
	      case "empty":
	    	  response.then()
	        	.body("email", equalTo("email não pode ficar em branco"))
	        	.body("password", equalTo("password não pode ficar em branco"));
	          break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	} 		
    }
}
