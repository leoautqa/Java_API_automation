package pageObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;
import java.util.Random;

import comum.common_steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class carrinho_po {
	public static Response response;
	
	private Faker faker = new Faker();	
	String name = "\"" + faker.name().fullName() + "\"";
    String email = "\"" + faker.internet().emailAddress() + "\"";
    String password = "\"" + faker.internet().password() + "\"";
    
    String id;
    String token;
    String prodId;
    String cartId;
	
	@When("Get list car shop")
    public void get_list_car_shop() {
    	response = given()
                  .contentType(ContentType.JSON)
                  .when()
                  .get("/carrinhos");
    	
    	common_steps.setResponse(response);
    }
		
	@Given("{} user")
    public void user(String user) {
    	String type = "\"false\"";
		
		if(user.equals("admin")) {
			type = "\"true\"";
    	} 
		
		String payload = "{";
	    payload += "\"nome\": " + name + ",";
	    payload += "\"email\": " + email + ",";
	    payload += "\"password\": " + password + ",";
	    payload += "\"administrador\": " + type;
	    payload += "}";
				
	    response = given()
	            .contentType(ContentType.JSON)
	            .body(payload)
	            .when()
	            .post("/usuarios");
		
		common_steps.setResponse(response);
		
		id = response.jsonPath().getString("_id");
		
		//System.out.println("Response body: " + response.getBody().asString());
    }
	
	 @And("Sing in account")
	    public void sign_in_accunt() {
	    	
	    	String payload = "{";
		    payload += "\"email\": " + email + ",";
		    payload += "\"password\": " + password;
		    payload += "}";
	    	
	    	response = given()
	                .contentType(ContentType.JSON)
	                .body(payload)
	                .when()
	                .post("/login");
	        
	        common_steps.setResponse(response);
	        
	        String authorization = response.jsonPath().getString("authorization");
	        
	        token = authorization.replace("Bearer ", "");
	    }
	 
	 public void postProduct(String payload) {
	    	response = given()
	        		.contentType(ContentType.JSON)
	                .header("Authorization", "Bearer " + token)
	                .body(payload)
	                .when()
	                .post("/produtos");
	    	
	    	common_steps.setResponse(response);
	    	
	    	prodId = response.jsonPath().getString("_id");
	    	
	    }
	
	 @When("Register {} product")
	    public void register_a_product(String caract) {
	    	String prodName = null;    	
	    	String randomNumber;
	    	    	
	    	if(caract.equals("a")) {
	    		Random random = new Random();
				randomNumber = " " + random.nextInt(101);
	    		
	    		prodName = "\"Aut test API Java" + randomNumber + "\",";
	    	}else if(caract.equals("exists")){
	    		prodName = "\"An exists product \",";
	    	}
	    	
	    	String payload = "{";
		    payload += "\"nome\": " + prodName;
		    payload += "\"preco\": " + 30 + ",";
		    payload += "\"descricao\": " + "\"Test\",";
		    payload += "\"quantidade\": " + 5;
		    payload += "}";
		    
		    postProduct(payload);
		    	    
		    String responseBody = response.getBody().asString();
		    	
		    if(caract.equals("exists") & responseBody.contains("Cadastro realizado com sucesso")) {
		    	postProduct(payload);
		    	
		    }	     
	    }
	 
	 public void postCart(String payload) {
			 response = given()
		        		.contentType(ContentType.JSON)
		        		.header("Authorization", "Bearer " + token)
		                .body(payload)
		                .when()
		                .post("/carrinhos");
	    	
	    	common_steps.setResponse(response);
	    	
	    	cartId = response.jsonPath().getString("_id");
	    	
	    	//System.out.println("Response body: " + response.getBody().asString());
	    }
	 
	@And("Register {} cart")
    public void register_cart(String action) {
    	int quant = 1;
    			
		if(action.equals("more product")) {
			quant = 30;
		}else if(action.equals("no authorization")) {
			token = "";
		}
	    
	    String payload = "{\n"
				+ "\"produtos\": [\n"
				+ "{\n"
					+ "\"idProduto\": " + "\"" + prodId + "\","
					+ "\"quantidade\": " + quant;
    				
    	if(action.equals("duplicate")) {
    		payload += "},\n"
    					+ "{\n"
    						+ "\"idProduto\": " + "\"" + prodId + "\","
    						+ "\"quantidade\": " + quant
    					+ "}\n"	
    				+ "]\n"
    			+ "}";
    	}else {
			payload += "}\n"	
				+ "]\n"
			+ "}";    	
    	}
    	    	    	
    	postCart(payload);
	    
	    String responseBody = response.getBody().asString();
	    	
	    if(action.equals("twice") & responseBody.contains("Cadastro realizado com sucesso")) {
	    	postCart(payload);
	    	
	    }    	
    }
	
	@And("Post cart {} message")
    public void post_product_message(String message) {
    	switch (message) {
	    	case "successful":
		  	  response.then()
		      		  .body("message", equalTo("Cadastro realizado com sucesso"));
		        break;
		    case "duplicate":
		  	  response.then()
		  	  		  .body("message", equalTo("Não é permitido possuir produto duplicado"));
		        break;
		    case "twice":
		    	response.then()
	  	  		  .body("message", equalTo("Não é permitido ter mais de 1 carrinho"));
		          break;
		    case "no product":
		    	response.then()
	  	  		  .body("message", equalTo("Produto não encontrado"));
		          break;
		    case "more product":
		    	response.then()
	  	  		  .body("message", equalTo("Produto não possui quantidade suficiente"));
		          break;
		    case "no authorization":
		    	response.then()
	  	  		  .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
		          break;
		    default:
		        System.out.println("Feature not recognized. No navigation performed.");
		        break;
    	}
    }
	
	@Then("Search cart {} ID")
	public void search_cart_ID(String generic){
		if(generic.equals("invalid")){
			cartId = "invalid";
		}
		
		response = given()
				  .pathParam("id", cartId)
	              .when()
	              .get("/carrinhos/{id}");
    	
    	common_steps.setResponse(response);
    	
    	//System.out.println("Response body: " + response.getBody().asString());
	}
	
	@And("Get no id message")
	public void get_no_id_message() {
		response.then()
		  .body("message", equalTo("Carrinho não encontrado"));
	}
	
	@And("Complete purchase")
	public void complete_purchase() {
		response = given()
				  .header("Authorization", "Bearer " + token)
	              .when()
	              .delete("/carrinhos/concluir-compra");
  	
		common_steps.setResponse(response);
		
		//System.out.println("Response body: " + response.getBody().asString());
	}
	
	@And("Purchase {} message")
	public void purchase_message(String purchase) {
		switch (purchase) {
	    	case "successful":
		  	  response.then()
		      		  .body("message", equalTo("Registro excluído com sucesso"));
		        break;
		    case "not found":
		  	  response.then()
		  	  		  .body("message", equalTo("Não foi encontrado carrinho para esse usuário"));
		        break;
		    case "twice":
		    	response.then()
	  	  		  .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
		          break;		    
		}			
	}
	
	@And("Cancel purchase")
	public void cancel_purchase() {
		response = given()
				  .header("Authorization", "Bearer " + token)
	              .when()
	              .delete("/carrinhos/cancelar-compra");
  	
		common_steps.setResponse(response);
		
		//System.out.println("Response body: " + response.getBody().asString());
	}
	
	@And("Cancel purchase {} message")
	public void cancel_purchase_message(String purchase) {
		switch (purchase) {
	    	case "successful":
		  	  response.then()
		      		  .body("message", equalTo("Registro excluído com sucesso. Estoque dos produtos reabastecido"));
		        break;
		    case "not found":
		  	  response.then()
		  	  		  .body("message", equalTo("Não foi encontrado carrinho para esse usuário"));
		        break;
		    case "twice":
		    	response.then()
	  	  		  .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
		          break;		    
		}			
	}
}
