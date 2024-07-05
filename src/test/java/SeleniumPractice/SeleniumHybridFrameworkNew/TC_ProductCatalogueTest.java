package SeleniumPractice.SeleniumHybridFrameworkNew;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents.BaseTest;
import SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents.Retry;

public class TC_ProductCatalogueTest extends BaseTest {

	@Test(priority = 1, groups = {"smoke", "regression"})
	public void checkProductAvailability() {

		String productToFind = "IPHONE 13 PRO"; 
		Assert.assertTrue(prodCataloguePage.getAllProductsName().contains(productToFind));
	}

	@Test(priority = 2, groups = {"sanity"})
	public void checkProductWithinGivenPriceRange() throws InterruptedException {

		long minprice = 20000;
		long maxprice = 50000;
		boolean res = true;
		prodCataloguePage.getProductsWithinGivenPriceRange(minprice, maxprice);
		List<Double> prodPrice = prodCataloguePage.getAllProductPrice();
		System.out.println(prodPrice);
		for (Double price : prodPrice) {
			if (price < minprice || price > maxprice) {
				res = false;
				break;
			}
		}
		
		Assert.assertTrue(res);

	}

	@Test(priority = 3, groups = {"sanity"}, retryAnalyzer = Retry.class)
	public void checkProductWithinInvalidPriceRange() {

		long minprice = 200;
		long maxprice = 500;
		prodCataloguePage.addPriceRange(minprice, maxprice);
		String actualMessage = prodCataloguePage.getErrorMessageForInvalidPriceRange(minprice, maxprice);
		Assert.assertTrue(actualMessage.equals("No Products Found"));
	}

}
