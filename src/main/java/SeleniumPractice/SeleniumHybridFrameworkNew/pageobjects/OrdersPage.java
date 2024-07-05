package SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public boolean isProductMatched(String prodname) {
		return productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(prodname));
	}
	
	
}
