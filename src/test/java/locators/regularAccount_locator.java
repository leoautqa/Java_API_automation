package locators;

import java.util.HashMap;
import java.util.Map;

public class regularAccount_locator {
	
	public static final Map<String, String> loginReg = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("inputEmail", "//input[contains(@data-testid,'email')]");
			put("inputPassword", "//input[contains(@data-testid,'senha')]");
			put("butEnter", "//button[contains(@data-testid,'entrar')]");
			put("messageInvalid", "//span[contains(.,'Email e/ou senha inválidos')]");
			put("userName", "//input[contains(@data-testid,'nome')]");
			put("userEmail", "//input[contains(@data-testid,'email')]");
			put("userPassword", "//input[contains(@data-testid,'password')]");
			put("checkbox", "//input[contains(@data-testid,'checkbox')]");
			put("btSign_Up", "//a[contains(@data-testid,'cadastrar')]");
			put("btRegister", "//button[contains(@data-testid,'cadastrar')]");
			put("ms_Cad_Suc", "//a[@href='/#'][contains(.,'Cadastro realizado com sucesso')]");
			put("login", "//h1[@class='font-robot'][contains(.,'Login')]");
    }};
    
    public static final Map<String, String> homeReg = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("inputSear", "//input[contains(@data-testid,'pesquisar')]");
			put("btSear", "//button[contains(@data-testid,'botaoPesquisar')]");
			put("msNoProdu", "//p[contains(.,'Nenhum produto foi encontrado')]");
			put("btLogOut", "//button[contains(@data-testid,'logout')]");
			put("detail", "//a[@class='card-link'][contains(.,'Detalhes')]");
			put("msDetail", "//h1[contains(.,'Detalhes do produto')]");
    }};
}
