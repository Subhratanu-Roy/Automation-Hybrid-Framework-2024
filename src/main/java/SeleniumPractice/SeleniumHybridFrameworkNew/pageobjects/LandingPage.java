package SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumPractice.SeleniumHybridFrameworkNew.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userpassword;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "div[role='alert']")
	WebElement alertMsg;
	
	@FindBy(css = ".invalid-feedback div")
	WebElement invalidUsernameMsg;
	
	public ProductCataloguePage loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		submit.click();
		return new ProductCataloguePage(driver);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getAlertMessage() {
		return alertMsg.getText();
	}
	
	public String getInvalidUsernameMessage() {
		return invalidUsernameMsg.getText();
	}
	
	public void clearTextBox() {
		userEmail.clear();
		userpassword.clear();
	}

}
