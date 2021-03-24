package plainScript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Zomato {
	// Author: Dhivya Prabha
	// Created Date: 02/12/2020
	// Test Name: zomato
	// Test Description: Find the highest priced sweet
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//Launch Chrome Browser in Headleass Mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		ChromeDriver driver = new ChromeDriver();
		//ChromeDriver driver = new ChromeDriver();
		// Launch Zomato.com
		driver.get("https://www.zomato.com/chennai");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.findElementByXPath("//i[@class='rbbb40-1 MxLSp']/following-sibling::input[1]").sendKeys("Velachery",Keys.TAB);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		// Send Restaurant Name
		driver.findElementByXPath("//input[@placeholder='Search for restaurant, cuisine or a dish']")
				.sendKeys("A2B - Adyar Anada Bhavan");

		driver.findElementByXPath("//input[@placeholder='Search for restaurant, cuisine or a dish']").click();
		Thread.sleep(3000);
		// Select Restaurant
		driver.findElementByXPath("(//p[text()='A2B - Adyar Ananda Bhavan'])[last()]").click();
		js.executeScript("window.scrollBy(0,500)");
		// Click order online
		driver.findElementByXPath("//a[text()='Order Online']").click();
		js.executeScript("window.scrollBy(0,500)");
		// Print Restaurant is open or closed
		String status = driver.findElementByXPath("//span[text()='Open now']").getText();
		if (status.contains("Open"))
			System.out.println("Restaurant is " + status + " For delivery");
		else
			System.out.println("Restaurant is Closed Now");
		// Print number of must try items
		List<WebElement> mustTry = driver.findElementsByXPath("//div[text()='MUST TRY']");
		System.out.println("The number of Must Try items is " + mustTry.size());
		// Print the highest priced sweet name
		driver.findElementByXPath("//section[@class='sc-jGDUUe cbYmli']/p[contains(text(), 'Sweets')]").click();
		List<WebElement> sweets = driver
				.findElementsByXPath("//h4[text()='Sweets']/following::span[@class='sc-17hyc2s-1 cCiQWA']");
		List<Integer> price = new ArrayList<Integer>();
		for (int i = 0; i < sweets.size(); i++) {
			String high = sweets.get(i).getText();
			high = high.substring(1).split("\\.")[0];

			price.add(Integer.parseInt(high));
		}

		Collections.sort(price);
		System.out.println(price);
		int highprice = price.get(price.size() - 1);
		String sweetname = driver
				.findElementByXPath("(//span[contains(text(), '" + highprice + "')]/preceding::h4)[last()]").getText();
		System.out.println("Highest priced sweet name is: " + sweetname);

	}

}
