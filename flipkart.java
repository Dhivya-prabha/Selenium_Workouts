package plainScript;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class flipkart {
	// Author: Dhivya Prabha
	// Created Date: 02/12/2020
	// Test Name: Flipkart
	// Test Description: Confirm Home theater product price order and add 2 products
	// to Compare
	public static void main(String[] args) throws InterruptedException {
		// Launch firefox browser
		WebDriverManager.firefoxdriver().setup();
		FirefoxDriver driver = new FirefoxDriver();
		// Launch Flipkart
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//button[@class='_2KpZ6l _2doB4z']").click();
		// Search Home Theater
		driver.findElementByXPath("//input[@title='Search for products, brands and more']").sendKeys("Home Theater",
				Keys.ENTER);
		// Get the number of results
		String total = driver.findElementByXPath("//div[@class='W_R1IA']//span").getText();
		String[] result = total.split(" ");
		int beforeamt = Integer.parseInt(result[5].replace(",", ""));
		// Click customer ratings 4* and above
		driver.findElementByXPath("//div[@class='_24_Dny']").click();
		Thread.sleep(3000);
		// Confirm the result count is reduced after selection
		String totalafter = driver.findElementByXPath("//div[@class='W_R1IA']//span").getText();
		String[] resultafter = totalafter.split(" ");
		int afteramt = Integer.parseInt(resultafter[5].replace(",", ""));
		if (beforeamt > afteramt) {
			System.out.println("Result count is reduced after Selection");
		}
		// Before High to Low font color and size
		WebElement beforefont = driver.findElementByXPath("//div[text()='Price -- High to Low']");
		String fontbefore = beforefont.getCssValue("font-weight");
		System.out.println("Before click font weight is " + fontbefore);
		// Click price High to Low
		driver.findElementByXPath("//div[text()='Price -- High to Low']").click();
		Thread.sleep(3000);
		// After clicking High to Low font color and size
		WebElement highlow = driver.findElementByXPath("//div[text()='Price -- High to Low']");
		String afterFont = highlow.getCssValue("font-weight");
		String font_clr = highlow.getCssValue("color");
		System.out.println(font_clr);
		if (Integer.parseInt(fontbefore) < Integer.parseInt(afterFont)) {
			System.out.println("Font size is increased after selection");
		}
		System.out.println("After click font weight is " + afterFont);
		// Confirm the price order of the products
		List<WebElement> val = driver.findElementsByXPath("//div[@class='_30jeq3']");
		List<Integer> price = new ArrayList<Integer>();
		for (int i = 0; i < val.size(); i++) {
			String high = val.get(i).getText().replaceAll("[^a-zA-Z0-9]", "");
			price.add(Integer.parseInt(high));
		}
		int count = 0;
		for (int i = 0; i <= price.size(); i++) {
			for (int j = i + 1; j < i; j++) {
				if (i < j) {
					count = count + 1;
				}
			}

		}
		if (count > 0) {
			System.out.println("Price is not in High to low order");
		} else
			System.out.println("Products listed in Price High to Low");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		// Select 2 products to compare
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElementByXPath("//div[text()='Bank Offer']"))
				.click(driver.findElementByXPath("//span[text()='Add to Compare']")).perform();
		act.moveToElement(driver.findElementByXPath("(//div[text()='Bank Offer'])[2]"))
				.click(driver.findElementByXPath("(//span[text()='Add to Compare'])[2]")).perform();
		act.moveToElement(driver.findElementByXPath("//span[text()='COMPARE']"));
		// Confirm 2 products are added into compare screen
		List<WebElement> compare = driver
				.findElementsByXPath("//span[text()='COMPARE']/preceding::div[@class='_3GNGJJ']");
		System.out.println("There are " + compare.size() + " products are Listed");

	}

}
