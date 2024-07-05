package SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents.AbstractComponent;

public class PlaceOrderPage extends AbstractComponent {

	WebDriver driver;

	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css = "section.ta-results button")
	List<WebElement> countryList;
	
	@FindBy(css = ".action__submit")
	WebElement placeorder;
	
	By locOfPlaceOrder = By.cssSelector(".action__submit");
	
	public void selectMyCountry(String countryName) {
		selectCountry.sendKeys(countryName);
		WebElement cont = countryList.stream()
				.filter(country -> country.findElement(By.cssSelector("span")).getText().equals(countryName)).findFirst()
				.orElse(null);
		cont.click();
	}
	
	public OrderConfirmationPage placeOrder() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500);");
		waitForElementToAppear(locOfPlaceOrder);
		waitForElementToBeClickable(placeorder);
		placeorder.click();
		return new OrderConfirmationPage(driver);
	}
	
	

}
