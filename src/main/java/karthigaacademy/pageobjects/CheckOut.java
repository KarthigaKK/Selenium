package karthigaacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import karthigaacademy.AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent{
	
	WebDriver driver;
	public CheckOut(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> itemsInCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	
	public List<WebElement> ItemsInCart()
	{
		return itemsInCart;
	}
	
	//Boolean match = 	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	
	public Boolean ProductMatch(String productName)
	{
		Boolean match=ItemsInCart().stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	public PaymentConfirmation clickCheckout()
	{
		checkoutbtn.click();
		PaymentConfirmation paymentConfirmation=new PaymentConfirmation(driver);
		return paymentConfirmation;
	}
	

}
