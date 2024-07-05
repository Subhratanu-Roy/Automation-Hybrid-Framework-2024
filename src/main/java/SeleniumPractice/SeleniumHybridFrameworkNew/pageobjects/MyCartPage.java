package SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {

	WebDriver driver;

	public MyCartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart")
	WebElement cart;

	@FindBy(xpath = "//h1")
	WebElement title;

	@FindBy(css = "li.totalRow button")
	WebElement checkout;

	@FindBy(css = "div.ng-star-inserted h1")
	WebElement msg;

	@FindBy(css = "button[routerlink='/dashboard']")
	WebElement continueShopping;
	
	By deleteButtonLoc = By.cssSelector(".cartSection .btn-danger");

	public void validateIfProductMatched(String productname) {

		boolean res = cart.findElements(By.cssSelector("h3")).stream().anyMatch(p -> p.getText().equals(productname));
		Assert.assertTrue(res);
	}

	public PlaceOrderPage clickOnCheckOut() {
		checkout.click();
		return new PlaceOrderPage(driver);
	}

	public String getPageTitle() {
		return title.getText();
	}

	public String getMessageForNoProductInCart() {

		List<WebElement> btnList = cart.findElements(By.cssSelector(".btn-danger"));
		while (cart.findElements(By.cssSelector(".btn-danger")).size() > 0) {
			waitForElementToBeClickable(cart.findElement(By.cssSelector("ul:nth-child(1) .btn-danger")));
			cart.findElement(By.cssSelector("ul:nth-child(1) .btn-danger")).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return msg.getText();
	}
	
	public ProductCataloguePage clickOnContinueShopping() {
		continueShopping.click();
		return new ProductCataloguePage(driver);
		
		
	}

}
