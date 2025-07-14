package selenium.com;

import java.net.URL;
import java.time.Duration;
import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
public class First {
	@Test
	public void test() throws Exception {
	UiAutomator2Options options = new UiAutomator2Options();
    options.setPlatformName("Android");
   // options.setPlatformVersion("9.0");
    options.setDeviceName("Android Emulator");
    options.setAutomationName("UiAutomator2");
    options.setApp("C:\\Users\\DELL\\Downloads\\sevaki_user_2.apk");
    options.setAppWaitActivity("*");
    options.setAutoGrantPermissions(true);

    URL url = new URL("http://127.0.0.1:4723/wd/hub");
    AndroidDriver driver = new AndroidDriver(url, options);
    try {
        System.out.println("App launched successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    //driver.findElement(AppiumBy.accessibilityId("I'm New, Register Now")).click();
    //driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"I’m New, Register Now\"]")).click();
    driver.findElement(By.xpath("//android.widget.Button[@content-desc='I’m New, Register Now']")).click();
    Thread.sleep(3000);
  // driver.findElement(By.xpath("//android.widget.EditText[@package='com.sevaki.user']")).sendKeys("dguegf");
  //  driver.findElement(By.xpath("//android.widget.EditText[@index='3']")).sendKeys("dguegf");
    //System.out.println("Name is entered");
    WebElement fullNameField = driver.findElement(By.xpath("//android.widget.EditText[@index='3']"));

    if (fullNameField.isDisplayed() && fullNameField.isEnabled()) {
        fullNameField.click(); // Focus the field
        fullNameField.clear(); // Optional: clear any pre-filled value
        fullNameField.sendKeys("John Doe");
        System.out.println("Name is entered");
    } else {
        System.out.println("Field is not ready for input.");
    }
    driver.findElement(By.xpath("//android.widget.EditText[@index='4']")).click();
    driver.findElement(By.xpath("//android.widget.EditText[@index='4']")).sendKeys("8989878987");
    driver.findElement(By.xpath("//android.widget.EditText[@index='5']")).click();
    driver.findElement(By.xpath("//android.widget.EditText[@index='5']")).sendKeys("test3@gmail.com");
    driver.findElement(By.xpath("//android.widget.CheckBox[@index='7']")).click();
    driver.findElement(By.xpath("//android.widget.Button[@content-desc='Register Now']")).click();
    
    
    
	 }
	
}

