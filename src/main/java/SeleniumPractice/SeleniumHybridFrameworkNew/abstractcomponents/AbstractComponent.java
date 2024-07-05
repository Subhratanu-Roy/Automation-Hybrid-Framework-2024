package SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.MyCartPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.OrdersPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.ProductCataloguePage;

public class AbstractComponent {

	WebDriver driver;
	public Actions actions;

	public AbstractComponent(WebDriver driver) {

		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(@routerlink, 'cart')]")
	WebElement cart;
	
	@FindBy(xpath = "//button[contains(@routerlink,'orders')]")
	WebElement orders;
	
	@FindBy(xpath = "//button[@routerlink = '/dashboard/']")
	WebElement home;


	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForELementToDisappear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public MyCartPage clickOnCartButton() {
		cart.click();
		return new MyCartPage(driver);
		
	}
	
	public OrdersPage clickOnOrders() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-250);");
		waitForElementToBeClickable(orders);
		orders.click();
		return new OrdersPage(driver);
		
	}
	
	public ProductCataloguePage clickOnHome() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-250);");
		waitForElementToBeClickable(home);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		home.click();
		return new ProductCataloguePage(driver);
		
	}
}
