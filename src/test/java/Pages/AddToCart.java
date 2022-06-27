package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

	WebDriver driver;

	// Constructor that will be automatically called as soon as the object of the
	// class is created
	public AddToCart(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for product linktext
	By prod = By.xpath("//*[@id=\"srp-river-results\"]/ul/li[2]/div/div[2]/a/h3");

	// Locator for Buy Button button
	By buyButton = By.id("binBtn_btn");

	// Locator for Button of Continue as guest
	By modalAsGuest = By.id("sbin-gxo-btn");

	// Locator for add to cart button
	By addToCart = By.id("isCartBtn_btn");

	// Locator for getting all product names
	By productInTheCart = By.xpath(
			"//*[@id=\"mainContent\"]/div/div[3]/div[1]/div/div/ul/li/div[1]/div/div/div[1]/div/div[2]/div/h3/span/a/span/span");

	// Method for product verifications which takes product name as parameter
	public void verifyProductInCart(String product) {

		ProductSearch search = new ProductSearch(driver);
		search.searchProduct(product);
		search.printNoOfItemsInSearchResults();

		// Capture parent window handle which helps in navigating to multiple
		// windows/tabs
		String originalWindow = driver.getWindowHandle();

		// Print selected product
		String selectedProduct = driver.findElement(prod).getText();
		System.out.println(selectedProduct);

		driver.findElement(prod).click();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart));

		driver.findElement(addToCart).click();

		// Print actual product from the cart
		String actualProduct = driver.findElement(productInTheCart).getText();
		System.out.println(actualProduct);

		// Validation for product
		if (actualProduct.contains(selectedProduct)) {

			System.out.println("Selected product : " + product + " is present in the cart");

		} else {
			System.out.println("Selected product : " + product + " is not present in the cart");
		}

	}

}
