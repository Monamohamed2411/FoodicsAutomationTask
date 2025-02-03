package Test;

import Pages.AddProductToCart;
import org.testng.annotations.Test;

public class TC03_AddProductToCartTest extends TestBase{


    AddProductToCart addnew;


    @Test
    public void add() throws InterruptedException {
        addnew= new AddProductToCart(driver);
        addnew.products();

        addnew.CheckTheAddedData();
    }
}
