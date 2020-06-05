package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	public WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}
	By lnkCustomer_menu=By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/a/span");
	By lnkCustomer_menu_item=By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/ul/li[1]/a/span");
	By btnAddNew=By.xpath("/html/body/div[3]/div[3]/div/form[1]/div[1]/div/a");
	By txtEmail=By.xpath("//*[@id=\"Email\"]");
	By txtPassword=By.id("Password");
	By txtFirstName=By.id("FirstName");
	By txtLastName=By.id("LastName");
	By rdBtnMaleGender=By.id("Gender_Male");
	By rdBtnFemaleGender=By.id("Gender_Female");
	By txtDOB=By.xpath("//*[@id=\"DateOfBirth\"]");
	By txtCompanyName=By.xpath("//*[@id=\"Company\"]");
	By chkIsTaxExempt=By.xpath("//*[@id=\"IsTaxExempt\"]");
	By txtNewsLetter=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[1]/div[9]/div[2]/div/div[1]/div");
	
	By txtCustomerRoles=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[1]/div[10]/div[2]/div/div[1]/div");
	By listItemsAdministrator=By.xpath("//*[@id=\"bf3a9c03-81f9-43e2-91df-4842b33b8928\"]");
	By ListItemsGuests=By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[3]");
	By ListItemsRegistered=By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[4]");
	By ListItemsVendors=By.xpath("//*[@id=\"SelectedCustomerRoleIds_listbox\"]/li[5]");
	By ListItemsForumModerators=By.xpath("//*[@id=\"bf3a9c03-81f9-43e2-91df-4842b33b8928\"]");
	
	By drpManagerOfVendor=By.xpath("//*[@id=\"VendorId\"]");
	By txtAdminComment=By.xpath("//*[@id=\"AdminComment\"]");
	By btnSave=By.xpath("/html/body/div[3]/div[3]/div/form/div[1]/div/button[1]");
	
	//Action Methods
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomerMenu()
	{
		ldriver.findElement(lnkCustomer_menu).click();
	}
	
	public void clickOnCustomerMenuItem()
	{
		ldriver.findElement(lnkCustomer_menu_item).click();
	}
	
	public void clickOnAddNew()
	{
		ldriver.findElement(btnAddNew).click();
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException
	{
		ldriver.findElement(txtCustomerRoles).click();
		
		WebElement listitem;
		Thread.sleep(3000);
		
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(listItemsAdministrator);
		}
		else if(role.equals("Guests"))
		{
			listitem=ldriver.findElement(ListItemsGuests);
		}
		else if(role.equals("Registered"))
		{
			listitem=ldriver.findElement(ListItemsRegistered);
		}
		else if(role.equals("Vendors"))
		{
			listitem=ldriver.findElement(ListItemsVendors);
		}
		else 
		{
			listitem=ldriver.findElement(ListItemsVendors);
		}
		
//		listitem.click();
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp = new Select(ldriver.findElement(drpManagerOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdBtnMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdBtnFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdBtnMaleGender).click();
		}
	}
	
	public void setDOB(String dob)
	{
		ldriver.findElement(txtDOB).sendKeys(dob);
	}
	
	public void setCompanyName(String cname)
	{
		ldriver.findElement(txtCompanyName).sendKeys(cname);
	}
	
	public void setAdminComment(String adcomment)
	{
		ldriver.findElement(txtAdminComment).sendKeys(adcomment);
	}
	
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
	
	
}
