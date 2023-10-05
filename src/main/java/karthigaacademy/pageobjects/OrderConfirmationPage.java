package karthigaacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import karthigaacademy.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	
   WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver)
	{
		super(driver);
	  	this.driver=driver;
	  	PageFactory.initElements(driver,this);
	  	
	}
	
	//driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String verifyConfirmationMessage()
	{
		String match= confirmationMessage.getText();
		return match;
	}

}
