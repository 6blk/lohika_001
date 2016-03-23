package lab_001;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class googleSearch {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//String searchStr = "Слово";
		//String searchStr = "бандера";
		String searchStr = "*8sdf7se78fcb";
		String URL = "wikipedia.org";
		List<String> links = null;
		boolean linkFound = false;

		WebDriver driver = new FirefoxDriver();

		driver.get("https://www.google.com.ua/");
		driver.findElement(By.xpath(".//*[@id='gbw']/div/div/div[2]/div[4]/div/a")).click();
								   //.//*[@id='gbw']/div/div/div[2]/div[4]/div/a

		driver.findElement(By.id("lst-ib")).sendKeys(searchStr);
		driver.findElement(By.xpath(".//*[@id='sblsbb']/button")).click();

		Thread.sleep(1000);                 //1000 milliseconds is one second.
		//List<WebElement> linksList = driver.findElements(By.className("_Rm"));
		List<WebElement> linksList = driver.findElements(By.cssSelector(".r>a"));
		
		/*for (int i = 0; i<linksList.size(); i++){
			System.out.println(linksList.get(i).getAttribute("href"));
		}*/

		links = new ArrayList<String>();
		for (int i = 0; i< linksList.size(); i++){
			if (linksList.get(i).getAttribute("href").contains(URL)){
				links.add(linksList.get(i).getAttribute("href"));
			}
		}

		if(links.isEmpty()){
			System.out.println("There is no any link.");
			linkFound = true;
		}
		else{
			for(int i = 0; i < links.size(); i++){
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "t");
				//ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				//driver.switchTo().window(tabs.get(0));
				driver.get(links.get(i));
				System.out.println("Wikipedia link has been opened: " + links.get(i).toString());
				linkFound = true;
			}
		}
		
		if (!linkFound){
			System.out.println("Wikipage was not found");
		}

		System.out.println("The job is done!");
		//driver.quit();
		//driver.close();
	}
}