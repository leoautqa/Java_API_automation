package comum;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class common_steps {

	private static Response response;
	
	public static void setResponse(Response res) {
        response = res;
    }

    public static Response getResponse() {
        return response;
    }
	
    @Given("Host")
    public void host() {
        RestAssured.baseURI = "https://serverest.dev";
    }
    
    @Then("The status code must be {int}")
    public void the_status_code_must_be(int statusCode) {
    	getResponse().then().statusCode(statusCode);
    }
}
