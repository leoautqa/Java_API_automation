//package pageObject;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import comum.CommonSteps;
////import comum.hooks;
//import comum.variables;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import locators.regularAccount_locator;
//
//public class regularAccount_po {
//	
//	private WebDriver driver;
//	private WebDriverWait wait;
//	
//	administratorAccount_po administratorAccount = new administratorAccount_po();
//	CommonSteps commonSteps = new CommonSteps(driver);
//	
//	public regularAccount_po() {
//		//this.driver = hooks.getDriver();
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		administratorAccount.implementation(driver);
//	}
//
//	@Given("I am logged in as regular")
//	public void i_am_logged_in_as_regular() {
//		if (driver.getCurrentUrl().contains("Home") || commonSteps.isUserLoggedIn()) {
//		    System.out.println("User already logged in, skipping login.");
//		    return;
//		}		
//		
//		String regularName = variables.REGULAR_NAME;
//		String regularUser = variables.REGULAR_USER;
//		String adminPass = variables.REGULAR_PASS;
//
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("inputEmail"))).sendKeys(regularUser);
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("inputPassword"))).sendKeys(adminPass);
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("butEnter"))).click();
//		
//		this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		
//		try {
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.loginReg.get("messageInvalid"))));
//	        registerNewAccountReg(regularName, regularUser, adminPass);
//	    } catch (org.openqa.selenium.TimeoutException e) {
//	    }
//	}
//	
//	private void registerNewAccountReg(String name, String email, String password) {
//
//		String regularName = variables.REGULAR_NAME;
//		String regularUser = variables.REGULAR_USER;
//		String adminPass = variables.REGULAR_PASS;
//
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("btSign_Up"))).click();
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("userName"))).sendKeys(regularName);
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("userEmail"))).sendKeys(regularUser);
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("userPassword"))).sendKeys(adminPass);
//		sendRegistration();
//	}
//	
//	public void sendRegistration() {
//		driver.findElement(By.xpath(regularAccount_locator.loginReg.get("btRegister"))).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.loginReg.get("ms_Cad_Suc"))));
//		the_Page_shoud_load_correctly();
//	}
//	
//	@Then("The page should load correctly")
//	public void the_Page_shoud_load_correctly() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("hCadUse"))));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("hLisUse"))));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("hCadProd"))));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("hLisProd"))));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("hRel"))));
//	}
//	
////"Comum"-------------------------------------------------------------------------------------------------------------------------------------
//	
//	@Given("On the {} tab")
//	public void on_The_Tab(String nameTab) {
//		driver.findElement(By.xpath("//a[contains(.,'"+ nameTab + "')]")).click();
//	}
//	
//	@And("Click in {}")
//	public void click(String button) {
//		driver.findElement(By.xpath("//button[contains(.,'" + button + "')]")).click();
//	}
//	
////Home tab-------------------------------------------------------------------------------------------------------------------------------------
//	
//	@When("Search for a product that does not exist")
//	public void search_For_a_product_That_Does_Not_Exist() {
//		driver.findElement(By.xpath(regularAccount_locator.homeReg.get("inputSear"))).sendKeys("not exist");
//	}
//	
//	@And("Search")
//	public void search(){
//		driver.findElement(By.xpath(regularAccount_locator.homeReg.get("btSear"))).click();
//	}
//	
//	@Then("Show no results")
//	public void show_No_Results() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("msNoProdu"))));
//	}
//	
//	@When("Search for a product")
//	public void search_For_A_Product() {
//		try {
//			driver.findElement(By.xpath(regularAccount_locator.homeReg.get("inputSear"))).sendKeys("No picture");
//			search();
//			boolean status = commonSteps.scrollElementIntoViewStatus(By.xpath(regularAccount_locator.homeReg.get("msNoProdu")));
//			if(status) {
//				click("Logout");
//				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.loginReg.get("login"))));
//				administratorAccount.i_am_logged_in_as_administrator();
//				on_The_Tab("Cadastrar Produto");
//				administratorAccount.fill_In_product("no picture");
//				administratorAccount.click_Button_Register();
//				administratorAccount.product_Must_Be_On_The_List();
//				click("Logout");
//				i_am_logged_in_as_regular();
//				on_The_Tab("Home");
//				driver.findElement(By.xpath(regularAccount_locator.homeReg.get("inputSear"))).sendKeys("no picture");
//				search();
//			}
//		}catch(org.openqa.selenium.TimeoutException e) {
//			
//		}
//	}
//	
//	@And("Detail the product")
//	public void detail_The_Product() {
//		driver.findElement(By.xpath(regularAccount_locator.homeReg.get("detail"))).click();		
//	}
//	
//	@Then("Product details must be visible")
//	public void product_Details_Must_Be_Visible(){
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(regularAccount_locator.homeReg.get("msDetail"))));
//	}
//	
//	@Then("Message {} must be visible")
//	public void message_Must_Be_Visible(String msg) {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(.,'" + msg + "')]")));
//	}
//	
//	@Then("The product must be in the shopping cart")
//	public void the_product_Must_Be_On_The_Shopping_Cart() {
//		System.out.println("Unfortunately this page is not done");
//	}
//}
