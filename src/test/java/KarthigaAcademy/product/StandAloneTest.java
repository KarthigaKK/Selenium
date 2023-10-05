package KarthigaAcademy.product;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KarthigaAcademy.testcomponents.BaseTest;
import KarthigaAcademy.testcomponents.Retry;
import karthigaacademy.pageobjects.CheckOut;
import karthigaacademy.pageobjects.OrderConfirmationPage;
import karthigaacademy.pageobjects.OrderPage;
import karthigaacademy.pageobjects.PaymentConfirmation;
import karthigaacademy.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" },retryAnalyzer=Retry.class)
	public void submitOrderForSelectedProduct(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginToApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CheckOut checkout = productCatalogue.goToCartPage();

		Boolean match = checkout.ProductMatch(input.get("product"));
		Assert.assertTrue(match);
		PaymentConfirmation payment = checkout.clickCheckout();
		payment.placeOrder("india");
		OrderConfirmationPage orderconfirmpage = payment.submitOrder();
		String confirmMessage = orderconfirmpage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrderForSelectedProduct" })
	public void OrderHistory() {
		ProductCatalogue productCatalogue = landingPage.loginToApplication("karthi.kcube2006@gmail.com", "Wolf123@");
		;
		OrderPage orderpage = productCatalogue.goToOrderPage();
		Boolean match = orderpage.VerifyorderMatch(productName);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String, String>> mapdata=getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//KarthigaAcademy//Data//PurchaseOrder.json");
			return new Object[][] {{mapdata.get(0)},{mapdata.get(1)}};
	}
	
	
	
	
	/*
	 * HashMap<String,String> map=new HashMap<String,String>(); map.put("email",
	 * "karthi.kcube2006@gmail.com"); map.put("password", "Wolf123@");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1=new HashMap<String,String>(); map1.put("email",
	 * "logu.rangan@gmail.com"); map1.put("password", "Wisdom123@");
	 * map1.put("product", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map},{map1}};
	 */
	// return new Object[][] {{"karthi.kcube2006@gmail.com","Wolf123@","ZARA COAT
	// 3"},{"logu.rangan@gmail.com","Wisdom123@","ADIDAS ORIGINAL"}};

}
