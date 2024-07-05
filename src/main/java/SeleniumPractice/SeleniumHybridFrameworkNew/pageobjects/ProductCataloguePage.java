package SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> prodList;

	@FindBy(xpath = "(//h6[text()='Price Range'])[2]/following-sibling::div/div[1]/input")
	WebElement minprice;

	@FindBy(css = "div.ng-trigger")
	WebElement errmsg;

	@FindBy(xpath = "(//h6[text()='Price Range'])[2]/following-sibling::div/div[2]/input")
	WebElement maxprice;

	By productsBy = By.cssSelector(".col-lg-4");

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	By toastContainer = By.cssSelector("#toast-container");

	By ngAnimating = By.cssSelector(".ng-animating");

	public List<String> getAllProductsName() {
		waitForElementToAppear(productsBy);
		return prodList.stream().map(product -> product.findElement(By.cssSelector(".card-body b")).getText())
				.collect(Collectors.toList());
	}

	public List<Double> getAllProductPrice() {
		return prodList.stream()
				.map(product -> product.findElement(By.cssSelector("div.card-body .text-muted")).getText())
				.map(prod -> Double.parseDouble(prod.substring(2))).collect(Collectors.toList());
	}

	public void addProductToCart(String productName) {
		waitForElementToAppear(productsBy);
		WebElement prod = prodList.stream()
				.filter(product -> product.findElement(By.cssSelector(".card-body b")).getText().equals(productName))
				.findFirst().orElse(null);

		prod.findElement(addToCart).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		waitForElementToAppear(toastContainer);
//		waitForELementToDisappear(ngAnimating);
	}

	public void addPriceRange(long min_price, long max_price) {
		minprice.clear();
		maxprice.clear();
		minprice.sendKeys(String.valueOf(min_price));
		maxprice.sendKeys(String.valueOf(max_price));
		actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();

	}

	public void getProductsWithinGivenPriceRange(long min_price, long max_price) throws InterruptedException {

		addPriceRange(min_price, max_price);
		Thread.sleep(2000);
		waitForElementToAppear(productsBy);

	}

	public String getErrorMessageForInvalidPriceRange(long min_price, long max_price) {

		addPriceRange(min_price, max_price);
		waitForElementToAppear(By.cssSelector("div.ng-trigger"));
		return errmsg.getText();
	}

}
