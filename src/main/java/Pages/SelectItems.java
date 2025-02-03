package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectItems extends PageBase{
    public SelectItems(WebDriver driver) {
        super(driver);
    }

    By SelectIem=  By.cssSelector("[class=\"hm-icon-label\"]");
    By SeeAll = By.cssSelector("[class*=\"hmenu-arrow-more\"]");
    By SelectVideo= By.cssSelector("[class=\"hmenu-item\"]");
    By SelectShippingFilter = By.id("apb-browse-refinements-checkbox_0");
    By SelectNew= By.cssSelector("[href*=\"/s?i=videogames&rh=n%3A18022560031%2Cp_n_free_shipping_eligible%3A21909080031%2Cp_n_condition-type%3A28071525031\"]");
    By SelectDropdown= By.id("a-autoid-0-announce");
    By SelectHigh= By.id("s-result-sort-select_2");


    public void SelectIconInMenu(){

        WebElement Select = driver.findElement(SelectIem);
        Select.click();

        WebElement SelectViewAll = driver.findElements(SeeAll).getFirst();
        SelectViewAll.click();

        WebElement SelectVideoIcon = driver.findElements(SelectVideo).get(17);
        SelectVideoIcon.click();

        try {
            WebDriverWait Wait= new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement SelectAllVideoIcon= Wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.hmenu-item[href*='node=18022560031']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SelectAllVideoIcon);
        }catch (TimeoutException e){
            System.out.println("not found");
        }

    }

    public void SelectFilterInMenu(){
        WebElement SelectFilterSipping = driver.findElement(SelectShippingFilter);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SelectFilterSipping);

        WebElement SelectNewCondition =driver.findElement(SelectNew);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SelectNewCondition);
    }


    public void SelectDropDownSorting(){
        WebElement ClickDropdown= driver.findElement(SelectDropdown);
        ClickDropdown.click();

        WebElement SelectHighOption = driver.findElement(SelectHigh);
        SelectHighOption.click();


    }

}
