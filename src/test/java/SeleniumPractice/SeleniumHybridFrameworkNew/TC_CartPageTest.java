package SeleniumPractice.SeleniumHybridFrameworkNew;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.MyCartPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.ProductCataloguePage;
import SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents.BaseTest;

public class TC_CartPageTest extends BaseTest {

	@Test(groups = {"smoke"})
	public void validateTitle() {

		MyCartPage myCartPage = prodCataloguePage.clickOnCartButton();
		String expectedResult = myCartPage.getPageTitle();
		String actualResult = "My Cart";
		myCartPage.clickOnHome();
		Assert.assertTrue(expectedResult.equals(actualResult));
	}

	@Test(groups = {"sanity"})
	public void validateMessageWhileNoProductExistsInCart() throws InterruptedException {
		String[] products = { "ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO" };
		for (String product : products) {
			prodCataloguePage.addProductToCart(product);
		}
		MyCartPage myCartPage = prodCataloguePage.clickOnCartButton();
		String expected = myCartPage.getMessageForNoProductInCart();
		String actual = "No Products in Your Cart !";
		myCartPage.clickOnHome();
		Assert.assertTrue(expected.equals(actual));

	}

	@Test(groups = {"smoke"})
	public void validateContinueShopping() {
		MyCartPage myCartPage = prodCataloguePage.clickOnCartButton();
		ProductCataloguePage productCataloguePage = myCartPage.clickOnContinueShopping();
		Assert.assertTrue(productCataloguePage.getAllProductsName().size() > 0);
	}

}
