package pageObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.github.javafaker.Faker;

import comum.common_steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class usuarios_po {
	
	public static Response response;

	private Faker faker = new Faker();	
	String name = "\"" + faker.name().fullName() + "\"";
    String email = "\"" + faker.internet().emailAddress() + "\"";
    String password = "\"" + faker.internet().password() + "\"";
    
    String id;    

    @When("Get list users")
    public void get_list_users() {
    	Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios");
    	common_steps.setResponse(response);
    }
	
    @When("Post a {} user")
	public void post_a_user(String user) {
		String type = "\"false\"";
		
		switch (user) {
	      case "admin":
	    	  		type = "\"true\"";
	          break;
	      case "null":
		    	  	name = null;
					email = null;
					password = null;
					type = null;
	          break;
	      case "empty":
		    	    name = "\"\"";
					email = "\"\"";
					password = "\"\"";
					type = "\"\"";
	            break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	} 
		
		String payload = "{";
	    payload += name != null ? "\"nome\": " + name + "," : "\"nome\": null,";
	    payload += email != null ? "\"email\": " + email + "," : "\"email\": null,";
	    payload += password != null ? "\"password\": " + password + "," : "\"password\": null,";
	    payload += type != null ? "\"administrador\": " + type : "\"administrador\": null";
	    payload += "}";
				
	    response = given()
	            .contentType(ContentType.JSON)
	            .body(payload)
	            .when()
	            .post("/usuarios");
		
		common_steps.setResponse(response);
		
		id = response.jsonPath().getString("_id");
	}
    
    @And("Post same user")
    public void post_same_user() {
    	post_a_user("regular");
    }
    
    @And("Search a {} user")
    public void search_user(String generic) {
    	
    	if(generic.equals("invalid")) {
    		id = "test";
    	}
    	
    	response = given()
    			.pathParam("id", id)
                .when()
                .get("/usuarios/{id}");
    	
    	common_steps.setResponse(response);
    }
        
    @And("Post {} message appear")
    public void post_message_registration_appear(String message) {
    	switch (message) {
	      case "registration":
	    	  response.then()
	        		  .body("message", equalTo("Cadastro realizado com sucesso"));
	          break;
	      case "same user":
	    	  response.then()
	    	  		  .body("message", equalTo("Este email já está sendo usado"));
	          break;
	      case "null":
	            response.then()
	            	    .body("nome", equalTo("nome deve ser uma string"))
		                .body("email", equalTo("email deve ser uma string"))
		                .body("password", equalTo("password deve ser uma string"))
		                .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));
	            break;
	      case "empty":
	    	    response.then()
			          	 .body("nome", equalTo("nome não pode ficar em branco"))
			             .body("email", equalTo("email não pode ficar em branco"))
			             .body("password", equalTo("password não pode ficar em branco"))
			             .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));
	          break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	} 		
    }
    
    @And("Get {} message appear")
    public void get_message_registration_appear(String message) {
    	switch (message) {
	      case "not found":
	    	  response.then()
	        		  .body("message", equalTo("Usuário não encontrado"));
	          break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	} 		
    }
    
    @When("Delete {} user")
    public void delete_user(String typeDel) {
    	
    	if(typeDel.equals("empty")) {
    		id = "";
    		
    	}else if (typeDel.equals("invalid")) {
    		id = "test";
    	}
    	
    	response = given()
    			.pathParam("id", id)
                .when()
                .delete("/usuarios/{id}");
    	
    	common_steps.setResponse(response);
    }
    
    @And("Delete {} message appear")
    public void delete_message_appear(String message) {
    	switch (message) {
	      case "not found":
	    	  response.then()
	        		  .body("message", equalTo("Não é possível realizar DELETE em /usuarios/. Acesse https://serverest.dev para ver as rotas disponíveis e como utilizá-las."));
	          break;
	      case "not delete":
	    	  response.then()
	        		  .body("message", equalTo("Nenhum registro excluído"));
	          break;
	      case "successful":
	    	  response.then()
	        		  .body("message", equalTo("Registro excluído com sucesso"));
	          break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	}
    }
    
    @And("Edit {} user")
    public void edit_user(String action) {
    	String type = "\"false\"";
    	
    	switch (action) {
	    	case "regular":
	    			name = name.substring(0, name.length() - 1) + " edit\"";
				break;
    		case "null":
		    	  	name = null;
					email = null;
					password = null;
					type = null;
	          break;
	      case "empty":
		    	    name = "\"\"";
					email = "\"\"";
					password = "\"\"";
					type = "\"\"";
	            break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	}  	 
    	    	
    	String payload = "{";
	    payload += name != null ? "\"nome\": " + name + "," : "\"nome\": null,";
	    payload += email != null ? "\"email\": " + email + "," : "\"email\": null,";
	    payload += password != null ? "\"password\": " + password + "," : "\"password\": null,";
	    payload += type != null ? "\"administrador\": " + type : "\"administrador\": null";
	    payload += "}";
				
	    response = given()
	            .contentType(ContentType.JSON)
	            .pathParam("id", id)
	            .body(payload)
	            .when()
	            .put("/usuarios/{id}");
	    
	    common_steps.setResponse(response);
    }
    
    @And("Put {} message appear")
    public void put_message_appear(String message) {
    	switch (message) {
	      case "successful":
	    	  response.then()
	        		  .body("message", equalTo("Registro alterado com sucesso"));
	          break;
	      case "null":
	    	  response.then()
		      	      .body("nome", equalTo("nome deve ser uma string"))
		              .body("email", equalTo("email deve ser uma string"))
		              .body("password", equalTo("password deve ser uma string"))
		              .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));
	          break;
	      case "succes":
	    	  response.then()
		          	  .body("nome", equalTo("nome não pode ficar em branco"))
		              .body("email", equalTo("email não pode ficar em branco"))
		              .body("password", equalTo("password não pode ficar em branco"))
		              .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));
	          break;
	      default:
	          System.out.println("Feature not recognized. No navigation performed.");
	          break;
    	}
    }
    
}


