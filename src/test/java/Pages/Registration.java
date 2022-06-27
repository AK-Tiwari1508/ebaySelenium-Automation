package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {

	WebDriver driver;

	// Constructor that will be automatically called as soon as the object of the
	// class is created
	public Registration(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for Register linktext
	By register = By.linkText("register");

	// Locator for first Name field
	By fName = By.id("firstname");

	// Locator for Last Name field
	By lName = By.id("lastname");

	// Locator for Email field
	By userEmail = By.id("Email");

	// Locator for password field
	By pwd = By.id("password");

	// Locator for register button after filling user details
	By regButton = By.xpath("//*[@id=\"EMAIL_REG_FORM_SUBMIT\"]");

	// Method to click register button
	public void clickRegisterLink() {
		driver.findElement(register).click();
	}

	// Method to enter firstName
	public void enterFirstName(String firstName) {
		driver.findElement(fName).sendKeys(firstName);
	}

	// Method to enter lastName
	public void enterLastName(String lastName) {
		driver.findElement(lName).sendKeys(lastName);
	}

	// Method to enter Email
	public void enterEmail(String email) {
		driver.findElement(userEmail).sendKeys(email);
	}

	// Method to enter Password
	public void enterPassword(String Password) {
		driver.findElement(pwd).sendKeys(Password);
	}

	// Method to click on Login button
	public void clickRegister() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(regButton).click();
	}

}
