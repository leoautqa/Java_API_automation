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

public class produto_po {
	public static Response response;

	private Faker faker = new Faker();	
	String name = "\"" + faker.name().fullName() + "\"";
    String email = "\"" + faker.internet().emailAddress() + "\"";
    String password = "\"" + faker.internet().password() + "\"";
    
    String id;
    String token;
    String prodId;
    
    @When("Get list products")
    public void get_list_users() {
    	Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/produtos");
    	
    	common_steps.setResponse(response);
    }
    
    @Given("An user {}")
    public void an_user_admin(String user) {
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
    
    @And("Admin user edit to regular")
    public void admin_user_edit_to_regular() {
    	String payload = "{";
	    payload += "\"nome\": " + name + ",";
	    payload += "\"email\": " + email + ",";
	    payload += "\"password\": " + password + ",";
	    payload += "\"administrador\": " + "\"false\"";
	    payload += "}";
				
	    response = given()
	            .contentType(ContentType.JSON)
	            .pathParam("id", id)
	            .body(payload)
	            .when()
	            .put("/usuarios/{id}");
	    
	    common_steps.setResponse(response);
    }
    
    @And("login account")
    public void login() {
    	
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
    
    @When("Creat {} product")
    public void creat_a_product(String caract) {
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
    
    @And("Post product {} message")
    public void post_product_message(String message) {
    	switch (message) {
	    	case "successful":
		  	  response.then()
		      		  .body("message", equalTo("Cadastro realizado com sucesso"));
		        break;
		    case "exists":
		  	  response.then()
		  	  		  .body("message", equalTo("Já existe produto com esse nome"));
		        break;
		    case "authorization":
		    	response.then()
	  	  		  .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
		          break;
		    case "administrador":
		    	response.then()
	  	  		  .body("message", equalTo("Rota exclusiva para administradores"));
		          break;
		    default:
		        System.out.println("Feature not recognized. No navigation performed.");
		        break;
    	}
    }
    
    @Then("Search a product {} ID")
    public void search_a_product_by_id(String generic) {
    	
    	if(generic.equals("without")) {
    		prodId = "test";
    	}
    	
    	response = given()
                .pathParam("id", prodId)
                .when()
                .get("/produtos/{id}");
    	
    	common_steps.setResponse(response);
    	
    }
    
    @And("Get product message not found")
    public void get_product_message_not_found() {
	  	  response.then()
	      		  .body("message", equalTo("Produto não encontrado"));
    }
    
    @When("Delete {} product")
    public void delete_product(String generic) {
    	if(generic.equals("no")) {
    		prodId = "test";
    	}else if(generic.equals("no authorization")) {
    		token = "";
    	}
    	
    	response = given()
    			.header("Authorization", "Bearer " + token)
    			.pathParam("id", prodId)
                .when()
                .delete("/produtos/{id}");
    	
    	common_steps.setResponse(response);
    	
    	//System.out.println("Response body: " + response.getBody().asString());
    }
    
    @And("Delete product {} message")
    public void delete_product_successful_message(String message) {
    	switch (message) {
    	case "successful":
	  	  response.then()
	      		  .body("message", equalTo("Registro excluído com sucesso"));
	        break;
	    case "empty":
	  	  response.then()
	  	  		  .body("message", equalTo("Nenhum registro excluído"));
	        break;
	    case "not permitted":
	    	response.then()
  	  		  .body("message", equalTo("Não é permitido excluir produto que faz parte de carrinho"));
	          break;
	    case "authorization":
	    	response.then()
  	  		  .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
	          break;
	    case "no admin":
	    	response.then()
  	  		  .body("message", equalTo("Rota exclusiva para administradores"));
	          break;
	    default:
	        System.out.println("Rota exclusiva para administradores");
	        break;
    	}
    }
    
    @And("Add product in a shop cart")
    public void add_product_in_a_shop_cart() {
    	String payload = "{\n";
    			payload += "\"produtos\": [\n";
    			payload += "{\n";
    				payload += "\"idProduto\": " + "\"" + prodId + "\",";
    				payload += "\"quantidade\": " + 5;
    				payload += "}\n";
    			 payload += "]\n";
    		   payload += "}";    	
    	
    	response = given()
        		.contentType(ContentType.JSON)
        		.header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post("/carrinhos");
    	
    	common_steps.setResponse(response);
    	
    	//System.out.println("Response body: " + response.getBody().asString());
    }
    
    @And("Edit {} product")
    public void edit_product(String action) {    	
    	Random random = new Random();
		String randomNumber = " " + random.nextInt(101);
		
		String prodName = "\"Aut test API Java" + randomNumber + " edited\",";
		Integer price = 30;
		String desc = "\"Test edit\",";
		Integer quant = 30;
		
		switch (action) {
    	case "no authorization":
    			token = "";
	        break;
	    case "empty":
		    	prodName = "\"\",";
	    		price = null;
	    		desc = "\"\",";
	    		quant = null;
	        break;
	    case "no admin":
	    	response.then()
  	  		  .body("message", equalTo("Rota exclusiva para administradores"));
	          break;
	    case "no ID":
	    		prodId = "";
	          break;
    	}
		
    	String payload = "{";
	           payload += "\"nome\": " + prodName;
	           payload += "\"preco\": " + price + ",";
	           payload += "\"descricao\": " + desc;
	           payload += "\"quantidade\": " + quant;
	          payload += "}";
    	
    	response = given()
        		.contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("id", prodId)
                .body(payload)
                .when()
                .put("/produtos/{id}");
    	
    	common_steps.setResponse(response);
    	
    	//System.out.println("Response body: " + response.getBody().asString());
    }
    
    @And("Put product {} message")
    public void put_product_successful_message(String message) {
    	switch (message) {
    	case "successful":
	  	  response.then()
	      		  .body("message", equalTo("Registro alterado com sucesso"));
	        break;
	    case "authorization":
	  	  response.then()
	  	  		  .body("message", equalTo("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
	        break;
	    case "no admin":
	    	response.then()
  	  		  .body("message", equalTo("Rota exclusiva para administradores"));
	          break;
	    case "empty":
	    	response.then()
        	  		.body("nome", equalTo("nome não pode ficar em branco"))
        	  		.body("preco", equalTo("preco deve ser um número"))
        	  		.body("descricao", equalTo("descricao não pode ficar em branco"))
        	  		.body("quantidade", equalTo("quantidade deve ser um número"));
	          break;
	    case "no ID":
	    	response.then()
  	  		  .body("message", equalTo("Não é possível realizar PUT em /produtos/. Acesse https://serverest.dev para ver as rotas disponíveis e como utilizá-las."));
	          break;
	    default:
	        System.out.println("Rota exclusiva para administradores");
	        break;
    	}
    }
}
