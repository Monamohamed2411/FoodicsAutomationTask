package Test;

import Pages.CheckOutPage;
import org.testng.annotations.Test;

public class TC04_CheckOutTest extends TestBase{

    CheckOutPage CheckData;
@Test
    public void CheckoutPage() throws InterruptedException {
        CheckData=new CheckOutPage(driver);
            CheckData.CheckOut();

        CheckData.AddAddress("mona","01127259058","streetlocation",
                "5","الجي", "العشر");

        CheckData.ItemPriceCalculation();
    }

}
