package karthigaacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import karthigaacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(css="input#userEmail")
	WebElement emailElement;
	@FindBy(id="userPassword")
	WebElement passwordElement;
	@FindBy(xpath="//input[@id='login']")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	//div[aria-label='Incorrect email or password.']
	//ng-tns-c4-5 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	
	public ProductCatalogue loginToApplication(String username,String password)
	{
		emailElement.sendKeys(username);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public String getErrorMessage()
	{
	    waitForWebElementToAppear(errorMessage);	
		return errorMessage.getText();
	}
	
	public void goInto()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	

}
