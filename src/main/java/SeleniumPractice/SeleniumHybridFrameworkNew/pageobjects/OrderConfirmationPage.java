package SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h1.hero-primary")
	WebElement message;
	
	public String getConfirmationMessage() {
		return message.getText();
	}

}
