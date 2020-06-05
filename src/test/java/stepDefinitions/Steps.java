package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws IOException
	{
		logger=Logger.getLogger("nopcommerce");
	    PropertyConfigurator.configure("Log4j.properties");//added logger
	    
		//Reading properties file
		configProp=new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configProp.load(configPropFile);
			
			
	    String br=configProp.getProperty("browser");
	    
	    if(br.equals("chrome"))
	    {
	    	System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
	 	   driver = new ChromeDriver();
	    }
	    else if(br.equals("firefox"))
	    {
	    	System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		 	   driver = new FirefoxDriver();
	    }
	    
	    logger.info("*********************Launching Browser********************");
		
	}
	
	
	@Given("^User Launch Chrome Browser$")
	public void user_Launch_Chrome_Browser()  {
		
		
	   
	   lp = new LoginPage(driver);
	}

	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url) {
		logger.info("*********************Launching URL********************");
	    driver.get(url);
	}

	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("*********************Providing UserName and Password********************");
	    lp.setUserName(email);
	    lp.setPassword(password);
	}

	@When("^Click on Login$")
	public void click_on_Login() {
	    lp.clickLogin();
	}

	@Then("^Page Title Should be \"([^\"]*)\"$")
	public void page_Title_Should_be(String title) {
	    if (driver.getPageSource().contains("Login was unseccessful.")) {
	    	driver.close();
	    	logger.info("*********************Login Not Successful********************");
	    	Assert.assertTrue(false);
	    } else {
	    	logger.info("*********************Login Successful********************");
	    	Assert.assertEquals(title, driver.getTitle());
	    }
	}

	@When("^User Click on Log out link$")
	public void user_Click_on_Log_out_link() throws InterruptedException {
		logger.info("*********************Clicking Logout********************");
	    lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("^Close browser$")
	public void close_browser() {
		logger.info("*********************Closing Browser********************");
	    driver.close();
	}
	
	//Add Customer
	@Then("^user can view Dashboard$")
	public void user_can_view_Dashboard() {
	    addCust = new AddCustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("^User click on customers Menu$")
	public void user_click_on_customers_Menu() throws InterruptedException {
		logger.info("*********************Clicking on Customers Menu********************");
	    Thread.sleep(3000);
		addCust.clickOnCustomerMenu();
	}

	@When("^click on customers menu item$")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("*********************Clicking on Customers Menu Item********************");
		addCust.clickOnCustomerMenuItem();
	}

	@When("^click on Add New Button$")
	public void click_on_Add_New_Button() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("*********************Clicking on Add New********************");
		addCust.clickOnAddNew();
	}

	@Then("^User can view Add New Customer Page$")
	public void user_can_view_Add_New_Customer_Page() {
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("^User enters customer info$")
	public void user_enters_customer_info() throws InterruptedException {
		logger.info("*********************Providing Customer Info********************");
	    String email = randomString()+"@gmail.com";
	    addCust.setEmail(email);
	    addCust.setPassword("123456");
	    addCust.setFirstName("Nimesh");
	    addCust.setLastName("Bhattarai");
	    addCust.setGender("Male");
	    addCust.setDOB("11/12/1980");
	    addCust.setCompanyName("busyQA");
	    Thread.sleep(3000);
	    addCust.setCustomerRoles("Guest");
	    addCust.setManagerOfVendor("Vendor 2");
	    addCust.setAdminComment("Admin comment testb ..............");
	    
	}

	@When("^click on save button$")
	public void click_on_save_button() throws InterruptedException {
		logger.info("*********************Saving Customer Details********************");
	   addCust.clickOnSave();
	   Thread.sleep(2000);
	}

	@Then("^user can view confirmation message \"([^\"]*)\"$")
	public void user_can_view_confirmation_message(String msg) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	    		.contains("The new customer has been added successfully."));
	}
	
	//Search Customer by Email
	
	@When("^Enter customer Email$")
	public void enter_customer_Email() {
		logger.info("*********************Providing email for searching customer********************");
	   searchCust = new SearchCustomerPage(driver);
	   searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("^Click on search button$")
	public void click_on_search_button() throws InterruptedException {
		
	    searchCust.clickSearch();
	    Thread.sleep(3000);
	}

	@Then("^User should find Email in the search table$")
	public void user_should_find_Email_in_the_search_table() {
	    boolean result = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	    Assert.assertEquals(true, result);
	}
	
	//Search Customer by First Name and Last Name
	@When("^Enter customer FirstName$")
	public void enter_customer_FirstName() {
		searchCust = new SearchCustomerPage(driver);
	    searchCust.setFirstName("Victoria");
	}

	@When("^Enter customer LastName$")
	public void enter_customer_LastName() {
	    searchCust.setLastName("Terces");
	}

	@Then("^User should find Name in the search table$")
	public void user_should_find_Name_in_the_search_table() {
		boolean result = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, result);
	}




}
