package SeleniumPractice.SeleniumHybridFrameworkNew.stepdefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.MyCartPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.OrderConfirmationPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.PlaceOrderPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.ProductCataloguePage;
import SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	ProductCataloguePage prodCataloguePage;
	OrderConfirmationPage orderConfirmationPage;

	@Given("^User is on the home page$")
	public void User_is_on_the_home_page() throws FileNotFoundException, IOException {
		prodCataloguePage = launchApplication();

	}

	@When("^User adds product (.+) to cart$")
	public void user_adds_product_to_cart(String productname) {

		prodCataloguePage.addProductToCart(productname);

	}

	@And("^Checkout (.+) and select country (.+) and click on place order$")
	public void checkout_product_and_select_country_and_click_on_place_order(String productname, String country) {
		MyCartPage myCartPage = prodCataloguePage.clickOnCartButton();
		myCartPage.validateIfProductMatched(productname);

		PlaceOrderPage placeOrderPage = myCartPage.clickOnCheckOut();
		placeOrderPage.selectMyCountry(country);
		orderConfirmationPage = placeOrderPage.placeOrder();

	}

	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		Assert.assertTrue(orderConfirmationPage.getConfirmationMessage().equals(string));
		driver.close();
	}

	@Then("^(.+) is displayed on the page$")
	public void product_is_displayed_on_the_page(String string) {

		Assert.assertTrue(prodCataloguePage.getAllProductsName().contains(string));
		driver.close();

	}

}
