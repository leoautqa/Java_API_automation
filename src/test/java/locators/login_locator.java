package locators;

import java.util.HashMap;
import java.util.Map;

public class login_locator {
	
	public static final Map<String, String> login = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("h1Login", "//h1[@class='font-robot'][contains(.,'Login')]");
			put("inputEmail", "//input[contains(@data-testid,'email')]");
			put("inputPassword", "//input[contains(@data-testid,'senha')]");
			put("butEnter", "//button[contains(@data-testid,'entrar')]");
			put("mandatoryEmail", "//span[contains(.,'Email é obrigatório')]");
			put("mandatoryPassword", "//span[contains(.,'Password é obrigatório')]");
			put("messageInvalid", "//span[contains(.,'Email e/ou senha inválidos')]");
			put("register", "//a[contains(@data-testid,'cadastrar')]");
			put("mandatoryName", "//div[@class='alert alert-secondary alert-dismissible'][contains(.,'×Nome é obrigatório')]");
			put("btRegistration", "//button[contains(@data-testid,'cadastrar')]");
			put("userName", "//input[contains(@data-testid,'nome')]");
			put("userEmail", "//input[contains(@data-testid,'email')]");
			put("userPassword", "//input[contains(@data-testid,'password')]");
			put("successfulMessage", "//a[@href='/#'][contains(.,'Cadastro realizado com sucesso')]");
			put("logout", "//button[contains(@data-testid,'logout')]");
			put("MessageAlreadyExists", "//span[contains(.,'Este email já está sendo usado')]");
			put("checkbox", "//input[contains(@data-testid,'checkbox')]");
    }};

}
