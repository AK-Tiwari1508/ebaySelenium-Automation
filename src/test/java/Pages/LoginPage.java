package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;

	// Constructor that will be automatically called as soon as the object of the
	// class is created
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for username field
	By uName = By.id("userid");

	// Locator for Continue Button field
	By continueButton = By.id("signin-continue-btn");

	// Locator for password field
	By pswd = By.id("pass");

	// Locator for login button
	By loginBtn = By.id("sgnBt");

	// Method to enter username
	public void enterUsername(String username) {
		driver.findElement(uName).sendKeys(username);
	}

	// Method to click continue button
	public void clickContinue() {
		driver.findElement(continueButton).click();
	}

	// Method to enter password
	public void enterPassword(String password) {
		driver.findElement(pswd).sendKeys(password);
	}

	// Method to click on Login button
	public void clickLogin() {
		driver.findElement(loginBtn).click();
	}

}
