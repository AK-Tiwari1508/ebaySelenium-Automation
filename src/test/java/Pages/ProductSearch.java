package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSearch {

	WebDriver driver;

	// Constructor that will be automatically called as soon as the object of the
	// class is created
	public ProductSearch(WebDriver driver) {
		this.driver = driver;
	}

	// Locator for Search field
	By searchField = By.id("gh-ac");

	// Locator for Search button
	By searchBtn = By.id("gh-btn");

	// Locator for results text
	By resulttxt = By.xpath("//h1[contains(text(),'results for')]");

	// Locator for getting all product names
	By allProductNames = By.xpath("//*[@id=\"srp-river-results\"]/ul/li/div/div[2]/a/h3");

	// Method to enter product in the search field
	public void searchProduct(String productName) {
		driver.findElement(searchField).sendKeys(productName);

	}

	// Method to print search results list.
	public void printNoOfItemsInSearchResults() {
		driver.findElement(searchBtn).click();
		String noOfItemsInResult = driver.findElement(resulttxt).getText();
		System.out.println(noOfItemsInResult);
		System.out.println("\n");
	}

	// Method to verify product match which takes product Name as parameter
	public void productMatch(String productName) {

		List<String> a = new ArrayList<String>();
		List<WebElement> allProducts = driver.findElements(allProductNames);

		for (int i = 0; i < allProducts.size(); i++) {
			a.add(allProducts.get(i).getText());
			System.out.println(allProducts.get(i).getText());
		}

		// String match in the ArrayList
		if (a.stream().filter(it -> it.contains(productName)) != null) {
			
			System.out.println("\n");
			System.out.println("Product is available in the list : Test passed");
			
		} else {
			
			System.out.println("Test failed");
			
		}

	}
}
