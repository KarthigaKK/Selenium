package KarthigaAcademy.product;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import KarthigaAcademy.testcomponents.BaseTest;
import KarthigaAcademy.testcomponents.Retry;
import karthigaacademy.pageobjects.CheckOut;
import karthigaacademy.pageobjects.ProductCatalogue;

public class ErrorMessageValidationTest extends BaseTest {

	
	@Test(groups= {"errorhandling"},retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException,InterruptedException{
		String productName = "Zara Coat 3";
		landingPage.loginToApplication("karthi.kcube@gmail.com", "Wol23@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test(retryAnalyzer=Retry.class)
	public void productErrorValidation() throws IOException,InterruptedException{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginToApplication("logu.rangan@gmail.com", "Wisdom123@");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CheckOut checkout = productCatalogue.goToCartPage();
		Boolean match=checkout.ProductMatch("Zara Coat 33");
	    Assert.assertFalse(match);
	}

}
