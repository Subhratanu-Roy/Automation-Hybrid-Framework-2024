package SeleniumPractice.SeleniumHybridFrameworkNew;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.MyCartPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.OrderConfirmationPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.OrdersPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.PlaceOrderPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents.BaseTest;
import SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents.Retry;

public class TC_SubmitOrderTest extends BaseTest {

	// String prodname = "ZARA COAT 3";

	@Test(groups = { "sanity","regression", "E2E" }, dataProvider = "getDataFromJson", retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws FileNotFoundException, IOException {

		prodCataloguePage.addProductToCart(input.get("product"));

		MyCartPage myCartPage = prodCataloguePage.clickOnCartButton();
		myCartPage.validateIfProductMatched(input.get("product"));

		PlaceOrderPage placeOrderPage = myCartPage.clickOnCheckOut();
		placeOrderPage.selectMyCountry(input.get("country"));

		OrderConfirmationPage orderConfirmationPage = placeOrderPage.placeOrder();
		String actualText = orderConfirmationPage.getConfirmationMessage();
		String expectedText = "THANKYOU FOR THE ORDER.";
		orderConfirmationPage.clickOnHome();
		Assert.assertTrue(actualText.equals(expectedText));

	}

	@Test(groups = { "sanity","regression" }, dependsOnMethods = { "submitOrder" }, dataProvider = "getDataFromJson")
	public void orderHistoryTest(HashMap<String, String> input) {
		OrdersPage ordersPage = prodCataloguePage.clickOnOrders();
		Assert.assertTrue(ordersPage.isProductMatched(input.get("product")));

	}

	@DataProvider
	public Object[] getData() {
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("country", "South Africa");
		map1.put("product", "ZARA COAT 3");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("country", "United States");
		map2.put("product", "ADIDAS ORIGINAL");

		Object[][] data = new Object[][] { { map1 }, { map2 } };
		return data;

	}

	@DataProvider
	public Object[][] getDataFromJson() throws IOException {

		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\SeleniumPractice\\SeleniumHybridFrameworkNew\\data\\PurchaseOrder.json";
		List<HashMap<String, Object>> data = jsonDataToMap(filepath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
