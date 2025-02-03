package Test.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Helper {

	//method to take screenshot when the test case fail

	public static void captureScreenshoot(WebDriver driver , String screenshootname) {

		Path dest = Paths.get("./Screenshots",screenshootname+".png");

		try {
			java.nio.file.Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		}
		catch (IOException e) {
			System.out.println("Exception while taking screenshot"+ e.getMessage());
		}
	}
}
