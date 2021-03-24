package plainScript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class bankbazaar {
	// Author: Dhivya Prabha
	// Created Date: 02/12/2020
	// Test Name: Bankbazaar
	// Test Description: Run the bankbazaar in IE browser
	public static void main(String[] args) throws InterruptedException {
		// Launch IE Browser
		WebDriverManager.edgedriver().setup();
		EdgeDriver driver = new EdgeDriver();
		// Launch BankBazaar
		driver.get("https://www.bankbazaar.com/personal-loan.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Choose Salaried employee as a type
		driver.findElementByXPath("//span[text()='Salaried']").click();
		// Give company name as Infosys
		driver.findElementByXPath("//input[@class='Select-input']").sendKeys("INFOSYS");
		Thread.sleep(2000);
		// Select Second listed option
		driver.findElementByXPath("//input[@class='Select-input']").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN,
				Keys.ENTER);
		// Click on the mid of the salary scale
		WebElement ele = driver.findElementByXPath("//div[@class='rangeslider__handle']");
		Actions act = new Actions(driver);
		act.clickAndHold(ele).moveByOffset(0, 118).release().perform();
		driver.findElementByLinkText("Continue").click();
		// Enter pincode
		driver.findElementByXPath("//input[@placeholder='PIN Code']").sendKeys("600001");
		driver.findElementByLinkText("Continue").click();
		// Enter Phone number
		driver.findElementByName("mobileNumber").sendKeys("12345678");
		driver.findElementByLinkText("Submit").click();
		// display error message
		String message = driver.findElementByClassName("errorMessage").getText();
		System.out.println(message);

	}

}
