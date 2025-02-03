package Test;

import Pages.SelectItems;
import org.testng.annotations.Test;


public class TC02_SelectItems extends TestBase {

    SelectItems selectDate ;
    @Test
    public void SelectMenuData() throws InterruptedException {
        selectDate= new SelectItems(driver);
        selectDate.SelectIconInMenu();

        selectDate.SelectFilterInMenu();

        selectDate.SelectDropDownSorting();

        Thread.sleep(2000);

    }
}
