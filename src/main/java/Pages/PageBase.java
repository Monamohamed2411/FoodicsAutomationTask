package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
	public WebDriver driver;
	public PageBase(WebDriver driver) {


		this.driver=driver;

	}
	
	public static void clickButton(WebElement button) {

		button.click();    
	}
	
	public static void setTextElement(WebElement text, String value ) {

		text.sendKeys(value);
	}
	
}
 