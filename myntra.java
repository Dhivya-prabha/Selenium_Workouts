package plainScript;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class myntra {
	// Author: Dhivya Prabha
	// Created Date: 02/12/2020
	// Test Name: Myntra
	// Test Description: Display THE SIZE OF Men Rectangle Sunglasses and the count
	// of similar items
	public static void main(String[] args) {
		// Launch the chrome in Incognito mode
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		@SuppressWarnings("deprecation")
		ChromeDriver driver = new ChromeDriver(capabilities);
		// Load myntra.com
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		// Print the window dimensions
		System.out.println(driver.manage().window().getSize());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Search Sunglasses and hit enter
		driver.findElementByTagName("input").sendKeys("Sunglasses", Keys.ENTER);
		// Filter the glasses with brand Polaroid
		driver.findElementByXPath("//span[contains(@class,'myntraweb-sprite filter-search-iconSearch')]").click();
		driver.findElementByClassName("filter-search-inputBox").sendKeys("Polaroid", Keys.ENTER);
		driver.findElementByXPath("//label[text()='Polaroid']/div").click();
		Actions act = new Actions(driver);
		WebElement sunglasses = driver.findElementByXPath("//h4[text()='Men Rectangle Sunglasses']");
		act.moveToElement(sunglasses).perform();
		// Print the product size of 'MenRectangle Sunglasses'
		String size = driver.findElementByXPath("//h4[text()='Men Rectangle Sunglasses']/following::span").getText();
		System.out.println("The Size of Men Rectangle Sunglasses is: " + size);
		// Mouse over on first result and click view similar
		act.moveToElement(driver.findElementByXPath("//picture[@class='img-responsive']//img"))
				.click(driver.findElementByXPath(
						"//span[@class='myntraweb-sprite image-grid-similarColorsIcon sprites-similarProductsIcon']"))
				.perform();
		// print the number of similar items
		List<WebElement> similar = driver.findElementsByXPath("//div[text()='Similar Products']/following::img");
		System.out.println(similar.size());

	}
}
