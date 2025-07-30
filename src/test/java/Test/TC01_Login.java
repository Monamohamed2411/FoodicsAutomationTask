package Test;

import Pages.LoginPage;
import org.testng.annotations.Test;

public class TC01_Login extends TestBase {

    LoginPage LoginDate ;
    @Test
    public void NavigateToLogin(){
        LoginDate=new LoginPage(driver);
        LoginDate.NavigateToLogin();

        LoginDate.UserLogin("01223726424",  "123456");

    }
}
