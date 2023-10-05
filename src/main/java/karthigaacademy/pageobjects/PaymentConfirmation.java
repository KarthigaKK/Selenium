package karthigaacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import karthigaacademy.AbstractComponents.AbstractComponent;

public class PaymentConfirmation extends AbstractComponent{
	
	WebDriver driver;
	
	public PaymentConfirmation(WebDriver driver)
	{
		super(driver);
	  	this.driver=driver;
	  	PageFactory.initElements(driver,this);
	  	
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By result=By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	public void placeOrder(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(result);
		selectCountry.click();
	}
	
	public OrderConfirmationPage submitOrder() throws InterruptedException 
	{
		//Thread.sleep(1000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		// Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", submit);
        Thread.sleep(2000);
        //waitForWebElementToAppear(submit);
        submit.click();
		return new OrderConfirmationPage(driver);	
	}
}
