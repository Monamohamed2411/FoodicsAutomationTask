package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.testng.Reporter;


public class LoginPage extends PageBase{
    public LoginPage(WebDriver drive) {
        super(drive);
    }
    By SignInHover = By.id( "nav-link-accountList");
    By SignInButton= By.cssSelector ( "[class=\"nav-action-signin-button\"]");
    By Email = By.cssSelector( "[class*=\"a-input-text\"]");
    By ContinueButton= By.id ( "continue");
    By Password= By.id ( "ap_password");
    By signInButton= By.id ( "signInSubmit");


    public void NavigateToLogin(){
        Actions actions = new Actions(driver);
        WebElement SignInHoverIcon= driver.findElement(SignInHover);
        actions.moveToElement(SignInHoverIcon).perform();


        try {
            WebDriverWait exp= new WebDriverWait(driver, Duration.ofSeconds(3));
            exp.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class=\"nav-action-signin-button\"]")));
            List<WebElement> SignInIcon= driver.findElements(SignInButton);
            clickButton(SignInIcon.getFirst());
            Reporter.log(" User direct To Login Page Successfully");



        } catch (TimeoutException e) {
            System.out.println("The Direction is Failed");
        }
    }
    public void UserLogin(String UserEmail, String UserPassword){

        WebElement EmailField= driver.findElement(Email);
        setTextElement(EmailField, UserEmail);
        WebElement ContinueButt= driver.findElement(ContinueButton);
        clickButton(ContinueButt);
        WebElement PasswordField= driver.findElement(Password);
        setTextElement(PasswordField, UserPassword);
        WebElement signInButt = driver.findElement(signInButton);
        clickButton(signInButt);
        Reporter.log(" User Login Page  Successfully");

    }



}
