package testPackage;

//All necessaray imports of classes/interfaces will captured here
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.AddToCart;
import Pages.LoginPage;
import Pages.ProductSearch;
import Pages.Registration;
import ReadExcelData.ExcelDataConfig;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteBayFeature {

	// Global declaration of variables
	WebDriver driver;
	WebDriverWait wait;

	// Using testNG annotations for better test scripts management
	@BeforeMethod
	public void testBrowser() {

		// Set property for chrome Driver
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/DriverSetup/chromedriver");
		driver = new ChromeDriver();

		// Setting implicit wait if driver is not able to interact with desired element
		// then it will throw
		// NoSuchElement exception
		// along with that setting pageload timeout too.

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));

		// Capture base URL as local variable to reuse further if required
		// Launching chrome browser with intended URL
		String baseURL = "https://www.ebay.com/";
		driver.get(baseURL);
		driver.manage().window().maximize();
		System.out.println("Chrome Browser has launched successfully");

		// Print title of the page to the console
		String expectedtitle = driver.getTitle();
		System.out.println("Title is " + expectedtitle);

		System.out.println("\n\n----------------------xxxxxxxxx------------------------\n\n");
	}

	// Use Case 1 : Login - Validate if I am able to log in validated I am logged in
	// Below method takes input as username and password for login from excel sheet
	// using data provider
	// Data driven testing

	@Test(dataProvider = "eBAYLoginData")
	public void loginTest(String username, String password)

	{
		// Lets start with Login flow from Home page of eBay site.
		driver.findElement(By.linkText("Sign in")).click();

		// Inserting user credentials for login which includes positive and negative
		// cases from excel sheet
		LoginPage login = new LoginPage(driver);
		login.enterUsername(username);
		login.clickContinue();
		login.enterPassword(password);
		login.clickLogin();

		try {

			// Verify username at the dashboard after login

			String expectedTitle = driver.findElement(By.xpath("//*[@id=\"gh-ug\"]/b[1]")).getText();

			if (expectedTitle.contains("Ak1")) {

				System.out.println("User " + username + " is able to login successfully");

			} else {

				System.out.println("User " + username + " is not able to login");

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}

	// Data Provider method to feed to data to login from excel sheet

	@DataProvider(name = "eBAYLoginData")

	public Object[][] passData() {

		// Create intance of ExcelDataConfig class which takes excel file path as
		// parameter

		ExcelDataConfig excel = new ExcelDataConfig("/TestData/LoginData.xlsx");

		int rows = excel.getRowCount(0);

		Object[][] data = new Object[rows][2];

		// Iterating through data available in sheet
		for (int i = 0; i < rows; i++) {

			data[i][0] = excel.getData(0, i, 0);
			data[i][1] = excel.getData(0, i, 1);

		}

		return data;
	}

	// Use Case 2 : Add to Cart - Validate if my product are being added correctly
	// into the system
	@Test
	public void addTocartTest() throws InterruptedException {

		// Enter you product as variable which needs to verify as a part of
		// addToCartTest method
		String product = "Watch";

		AddToCart cart = new AddToCart(driver);
		cart.verifyProductInCart(product);

		System.out.println("\n\n----------------------xxxxxxxxx------------------------\n\n");

	}

	// Use Case 3 : Registration flow - Validate as a new user I am able to register
	// myself into ebay.
	@Test
	public void registrationTest() throws InterruptedException {

		Registration register = new Registration(driver);
		register.clickRegisterLink();

		// Currently, verifying this use case with one user details later on we can use
		// Data provider to pass multiple data through excel sheet

		// creating an object of Random class
		Random random = new Random();
		String userFirst = "userF" + random.nextInt(1000);
		String userLast = "userL" + random.nextInt(1000);
		String userEmail = "userE" + random.nextInt(1000) + "@gmail.com";

		register.enterFirstName(userFirst);
		register.enterLastName(userLast);
		register.enterEmail(userEmail);
		register.enterPassword("UserU@9100");
		register.clickRegister();

		System.out.println("New User can see dashboard now ");

		// Adding wait for the visibility check
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gh-ug\"]/b[1]")));

		// Hard assert for userName validation
		String ExpectedUName = driver.findElement(By.xpath("//*[@id=\"gh-ug\"]/b[1]")).getText();
		Assert.assertTrue(ExpectedUName.contains(userFirst), "User is not registered");

		System.out.println(userFirst + " is verified now");

		System.out.println("----------------------xxxxxxxxx------------------------");

	}

	// Use Case 4 : Search - Validate the product entered as key word is resulting
	// the key list and
	// string match happens
	@Test
	public void productSearchTest() throws InterruptedException {

		// Enter the product as variable which you want to search on eBay site
		String product = "watch";
		ProductSearch search = new ProductSearch(driver);
		search.searchProduct(product);

		// Print search result from first page
		search.printNoOfItemsInSearchResults();

		// Validation of product in the result list
		search.productMatch(product);

		System.out.println("\n\n----------------------xxxxxxxxx------------------------\n\n");

	}

	// Test script closure method
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
