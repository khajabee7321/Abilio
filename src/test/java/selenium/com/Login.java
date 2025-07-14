package selenium.com;

import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class Login {
	@Test
	public void test1() throws Exception {
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
    
    driver.findElement(By.xpath("//android.widget.Button[@index='2']")).click();
    Thread.sleep(4000);

    //driver.findElement(By.xpath("//android.widget.EditText[@index='3']")).sendKeys("7989981189");
   Thread.sleep(4000);
   driver.findElement(By.className("android.widget.EditText")).click();
   driver.findElement(By.className("android.widget.EditText")).sendKeys("7989981189");
  // driver.findElement(By.xpath("//android.widget.EditText[@index='3']")).sendKeys("7989981189");
  Thread.sleep(2000);
  driver.findElement(By.className("android.widget.Button")).click();
  
  
  
  
  
  Thread.sleep(3000);  // Wait for OTP to arrive

  // Step 4: Fetch OTP from API
  String otp = getOTPFromAPI("7989981189");
  System.out.println("OTP fetched: " + otp);

  // Step 5: Enter OTP digit by digit
  List<WebElement> otpInputs = driver.findElements(By.className("android.widget.EditText"));
  for (int i = 0; i < otp.length(); i++) {
      otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
  }

  // Step 6: Click Verify OTP
  driver.findElement(By.xpath("//android.widget.Button[@text='Verify OTP']")).click();
  System.out.println("OTP verification attempted.");
}

// ✅ Utility method - fetch OTP from API
public String getOTPFromAPI(String mobileNumber) {
  RestAssured.baseURI = "https://sevakiserver.sevaki.in";
  String response = RestAssured
          .given()
          .queryParam("to", mobileNumber)
          .when()
          .get("/index.php/api/login_with_mobile")
          .then()
          .extract()
          .asString();

  JsonPath json = new JsonPath(response);
  return json.getString("otp"); // Change to actual key if needed
  }

}

