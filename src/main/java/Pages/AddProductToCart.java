package Pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.time.Duration;
import java.util.List;

public class AddProductToCart extends PageBase{
    private static int count=0;
    private static int cartAmountBeforeShipping=0;

    public AddProductToCart(WebDriver driver) {
        super(driver);
    }

    By CartNumber= By.id("nav-cart-count");
    public void products() throws InterruptedException {
        WebElement cartAmountBefore= driver.findElement(CartNumber);
        cartAmountBeforeShipping= Integer.parseInt(cartAmountBefore.getText());

        while (true) {
            WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<WebElement> productElements = Wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']")));
            boolean AddedProduct= false;
            for (WebElement product : productElements) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", product);
                try {
                    WebElement priceElements = product.findElement(By.cssSelector("[class=\"a-price-whole\"]"));
                    String priceText = priceElements.getText().replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(priceText);
                    if (price < 15000) {
                        WebElement addToCartButton = product.findElement
                                (By.cssSelector("[class*=\"a-button-icon\"] [class=\"a-button-inner\"] [class=\"a-button-text\"]"));
                        if (addToCartButton.isDisplayed()) {
                            Wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
                            AddedProduct=true;
                            count++;
                            Thread.sleep(1000);
                            Reporter.log("Product added to cart with price: " + price);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("This product Didn't add to card");
                    Reporter.log("This product Didn't add to card");
                }
            }
            try {
                WebElement nextPageButton = driver.findElement(By.cssSelector("[class*=\"s-pagination-next\"]"));
                if (!AddedProduct) {
                    nextPageButton.click();
                    Thread.sleep(2000);
                } else {
                    System.out.println("The Data Added to Cart Successfully");
                    Reporter.log("The Data Added to Cart Successfully");
                    break;
                }
            } catch (Exception e) {
                System.out.println("No more pages available");
                break;
            }
        }

    }

    public void CheckTheAddedData(){
        WebElement cartAmount= driver.findElement(CartNumber);
        int Actual= count;
        int cartAmountAfterShipping= Integer.parseInt(cartAmount.getText());
        int Expected = cartAmountAfterShipping - cartAmountBeforeShipping;
        Assert.assertEquals(Expected,Actual);

    }
        }
















