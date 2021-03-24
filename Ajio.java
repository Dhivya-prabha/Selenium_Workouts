package plainScript;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ajio {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.ajio.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Actions act = new Actions(driver); 
		act.moveToElement(driver.findElementByXPath("//a[@title='WOMEN']")).perform();
		Thread.sleep(3000);
		act.moveToElement(driver.findElementByXPath("(//span[text()='Shop by: '])[2]/following::a[contains(text(),'BRANDS')]")).perform();
		Thread.sleep(5000);
		List<String>items = new ArrayList<String>();
		List<WebElement> titles = driver.findElementsByXPath("//a[text()[normalize-space()='WOMEN']]/following::a[contains(text(),'BRANDS')]/following::a");
		for (int i = 0; i < titles.size(); i++) {
			String dress = titles.get(i).getText();
			items.add(dress);		
		}
		int index = items.indexOf("");
		if (index > -1) {
			items.subList(index + 1, items.size()).clear();
		}
		System.out.println(items);
		
		String maxOfLowercase = items.stream()
		        .max(Comparator.comparingLong(o -> o.chars().filter(Character::isLowerCase).count()))
		        .get();
		System.out.println(maxOfLowercase);
		
		
	}
}
