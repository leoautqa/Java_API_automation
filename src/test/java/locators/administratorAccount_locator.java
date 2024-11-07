package locators;

import java.util.HashMap;
import java.util.Map;

public class administratorAccount_locator {
						
	public static final Map<String, String> loginAdm = new HashMap<String, String>() {
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
    }};
    
    public static final Map<String, String> homeAdm = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("hCadUse", "//h5[@class='card-title'][contains(.,'Cadastrar Usuários')]");
			put("hLisUse", "//h5[@class='card-title'][contains(.,'Listar Usuários')]");
			put("hCadProd", "//h5[@class='card-title'][contains(.,'Cadastrar Produtos')]");
			put("hLisProd", "//h5[@class='card-title'][contains(.,'Listar Produtos')]");
			put("hRel", "//h5[@class='card-title'][contains(.,'Relatórios')]");
			put("hRel", "//h5[@class='card-title'][contains(.,'Relatórios')]");
    }};
    
    public static final Map<String, String> cadUsu = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("btCad", "//button[contains(@data-testid,'cadastrarUsuario')]");
			put("msgNameMan", "//span[contains(.,'Nome é obrigatório')]");
			put("msgEmailMan", "//span[contains(.,'Email é obrigatório')]");
			put("msgPassMan", "//span[contains(.,'Password é obrigatório')]");
			put("msAlrExi", "//span[contains(.,'Este email já está sendo usado')]");
			put("inpName", "//input[contains(@data-testid,'nome')]");
			put("inpEmail", "//input[contains(@data-testid,'email')]");
			put("inpPass", "//input[contains(@data-testid,'password')]");
			put("cheBoxAdm", "//input[contains(@data-testid,'checkbox')]");
    }};
    
    public static final Map<String, String> lisUsu = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("hUmLisUsu", "//h1[contains(.,'Lista dos usuários')]");

    }};
    
    public static final Map<String, String> cadProd = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("btCad", "//button[contains(@data-testid,'cadastarProdutos')]");
			put("msNoName", "//span[contains(.,'Nome é obrigatório')]");
			put("msNoPrice", "//span[contains(.,'Preco é obrigatório')]");
			put("msNoDesc", "//span[contains(.,'Descricao é obrigatório')]");
			put("msNoQuant", "//span[contains(.,'Descricao é obrigatório')]");
			put("nameProd", "//input[contains(@data-testid,'nome')]");
			put("priceProd", "//input[contains(@data-testid,'preco')]");
			put("descProd", "//textarea[contains(@data-testid,'descricao')]");
			put("quantProd", "//input[contains(@data-testid,'quantity')]");
			put("msPrice", "//span[contains(.,'Preco deve ser um número positivo')]");
			put("msQuant", "//span[contains(.,'Quantidade deve ser maior ou igual a 0')]");
			put("choFil", "//input[contains(@data-testid,'imagem')]");
			put("choFil", "//input[contains(@data-testid,'imagem')]");
    }};
    
    public static final Map<String, String> lisProd = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;{
			put("titLis", "//h1[contains(.,'Lista dos Produtos')]");
			put("msProdExis", "//span[contains(.,'Já existe produto com esse nome')]");

	}};
}
