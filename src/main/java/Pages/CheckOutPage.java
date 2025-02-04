package Pages;

import org.checkerframework.checker.units.qual.N;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import java.time.Duration;
import java.util.List;

public class CheckOutPage extends PageBase{
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    By ClickAddAddress= By.id("add-new-address-desktop-sasp-tango-link");
    By UserName = By.id("address-ui-widgets-enterAddressFullName");
    By UserPhone = By.id("address-ui-widgets-enterAddressPhoneNumber");
    By UserStreet = By.id("address-ui-widgets-enterAddressLine1");
    By UserBuildNumber = By.id("address-ui-widgets-enter-building-name-or-number");
    By UserCity = By.id("address-ui-widgets-enterAddressCity");
    By UserDistrictOrCounty = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    By UserConfirmAddress= By.id("checkout-primary-continue-button-id");






    By CartButton = By.id("nav-cart-count");
    By CheckOut = By.id("sc-buy-box-ptc-button");

    By PaymentMethod= By.name("ppw-instrumentRowSelection");
    By ConfirmPayment= By.cssSelector("[id=\"checkout-primary-continue-button-id\"] [class=\"a-button-input\"]");
    By ItemPrice = By.cssSelector("[class*=\"aok-nowrap\"]");
    By Delivery= By.cssSelector("[class*=\"a-color-success\"] [class*=\"a-text-right\"]");
    By PriceOfItems= By.cssSelector("[class*=\"lineitem-price-text\"]");
    By OrderTotal= By.cssSelector("[class*=\"a-size-medium\"] [class=\"order-summary-line-definition\"] ");
    By CartButtonInCheckout= By.id("nav-checkout-cart-icon");
    By RemoveItem= By.cssSelector("[class*=\"a-icon-checkbox\"]");


    public void CheckOut() throws InterruptedException {
        WebElement ClickCartButton= driver.findElement(CartButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", ClickCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ClickCartButton);
        WebElement CheckOutButton = driver.findElement(CheckOut);
        CheckOutButton.click();
        Thread.sleep(1000);

    }

    public void AddAddress(String FullName, String PhoneNumber, String StreetLocation,
                           String TheBuildNumber, String TheCity, String theDistrictOrCounty) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
            WebElement TheAddAddressButton= driver.findElement(ClickAddAddress);
            if (TheAddAddressButton.isDisplayed()){
                TheAddAddressButton.click();
                WebElement Name = driver.findElement(UserName);
                setTextElement(Name,FullName);
                WebElement Phone= driver.findElement(UserPhone);
                setTextElement(Phone, PhoneNumber);
                WebElement Street= driver.findElement(UserStreet);
                setTextElement(Street, StreetLocation);
                WebElement BuildNumber= driver.findElement(UserBuildNumber);
                setTextElement(BuildNumber, TheBuildNumber);
                WebElement City= driver.findElement(UserCity);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", City);
                BuildNumber.click();
                setTextElement(City, TheCity);
                Thread.sleep(1000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", City);
                WebElement SelectCity= wait.until( ExpectedConditions.presenceOfElementLocated(By.id("address-ui-widgets-autoCompleteResult-0")));
                SelectCity.click();

                WebElement DistrictOrCounty = driver.findElement(UserDistrictOrCounty);

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", DistrictOrCounty);
                setTextElement(DistrictOrCounty, theDistrictOrCounty);
                Thread.sleep(1000);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", DistrictOrCounty);

                wait.until( ExpectedConditions.presenceOfElementLocated(By.id("address-ui-widgets-autoCompleteResult-0"))).click();

                WebElement ConfirmTheAddress = driver.findElement(UserConfirmAddress);
                ConfirmTheAddress.click();
            }
            else {
                System.out.println("the address is added successfully");

            }

        } catch (NoSuchElementException e) {
            System.out.println("the address is added successfully");
        }



    }
    public void ItemPriceCalculation() {
        int retryCount = 0;
        int maxRetries = 2;
        while (maxRetries > retryCount){
            try {

                List< WebElement> SelectCash = driver.findElements(PaymentMethod);
                if(SelectCash.get(1).isEnabled()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SelectCash.get(1));
                    Thread.sleep(1000);
                    WebElement ConfirmCash= driver.findElement(ConfirmPayment);
                    ConfirmCash.click();

                    WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    List<WebElement> PriceForItems = Wait.until(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(PriceOfItems));
                    double SumOfItems=0;
                    for (WebElement priceForItem : PriceForItems) {
                        String priceText = priceForItem.getText()
                                .replaceAll("[^0-9.,]", "")
                                .replaceAll(",", "");
                        double price = Double.parseDouble(priceText);
                        System.out.println("the price is " + price);
                        SumOfItems += price;
                    }
                    System.out.println("the items Sum "+ SumOfItems);

                    List <WebElement> ItemsPrice = driver.findElements(ItemPrice);
                    String ShippingText = ItemsPrice.get(1).getText()
                            .replaceAll("[^0-9.,]", "")
                            .replaceAll(",", "");
                    double ShippingPrice = Double.parseDouble(ShippingText);
                    System.out.println("the Shipping & handling "+ ShippingPrice);

                    String CashShippingText = ItemsPrice.get(2).getText()
                            .replaceAll("[^0-9.,]", "")
                            .replaceAll(",", "");
                    double ShippingCashPrice = Double.parseDouble(CashShippingText);
                    System.out.println("the Shipping & handling "+ ShippingCashPrice);



                    WebElement FreeShipping = driver.findElement(Delivery);
                    String ShippingFree = FreeShipping.getText()
                            .replaceAll("[^0-9.,]", "")
                            .replaceAll(",", "");
                    double ShippingPriceFree = Double.parseDouble(ShippingFree);
                    System.out.println("Free Delivery "+ ShippingPriceFree);


                    double ActualSum= SumOfItems + ShippingPrice + ShippingCashPrice - ShippingPriceFree;
                    System.out.println( " the sum is " + ActualSum);


                    WebElement TotalPrice= driver.findElement(OrderTotal);
                    String TotalPriceData = TotalPrice.getText()
                            .replaceAll("[^0-9.,]", "")
                            .replaceAll(",", "");
                    double ExpectedTotalPriceForItems = Double.parseDouble(TotalPriceData);
                    System.out.println("Order total "+ ExpectedTotalPriceForItems);

                    Assert.assertEquals(ExpectedTotalPriceForItems, ActualSum , 0.01);
                    break;
                }else{

                    System.out.println("the cash button is dimmed");
                    WebElement CartCheckoutButton= driver.findElement(CartButtonInCheckout);
                    CartCheckoutButton.click();
                    WebElement TotalAmount= driver.findElement(By.cssSelector("[id=\"sc-subtotal-amount-buybox\"] [class*=\"sc-white-space-nowrap\"]"));

                    String TotalAmountData = TotalAmount.getText()
                            .replaceAll("[^0-9.,]", "")
                            .replaceAll(",", "");
                    double TotalAmountForAll = Double.parseDouble(TotalAmountData);
                    System.out.println("TotalAmountForAll"+ TotalAmountForAll);
                    if (TotalAmountForAll > 25000){

                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                        wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(RemoveItem)));
                        List<WebElement> RemoveProductItem = driver.findElements(RemoveItem);
                        for (int i=0 ; i < RemoveProductItem.size() -1 ; i++){
                            try {
                                RemoveProductItem.get(i).click();
                                Thread.sleep(1000);
                            } catch (StaleElementReferenceException | InterruptedException e) {
                                System.out.println("Retrying due to StaleElementReferenceException...");

                            }
                        }
                        WebElement CheckOutButton = driver.findElement(CheckOut);
                        CheckOutButton.click();

                    }
                    retryCount++;

                }
            } catch (Exception e) {
                System.out.println("the cash button didn't exist");
            }
        }


    }
}
