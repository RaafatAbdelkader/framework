package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SetupDriver {
    public static ThreadLocal<WebDriver>driverThread=new ThreadLocal<>();
     WebDriver initDriver(){
        String browser=PropReader.getProp("browser").toString();
        String headless=PropReader.getProp("headless").toString();
        String hub_ip=PropReader.getProp("selenium_hub_ip").toString();
        URL hub_url=null;
        try {
            hub_url=new URL(hub_ip);
        } catch (MalformedURLException e) {
             e.printStackTrace();
        }
         DesiredCapabilities capabilities=new DesiredCapabilities();
         if (browser.contains("chrome")){
            capabilities.setBrowserName("chrome");
            ChromeOptions chromeOptions = new ChromeOptions();
            if (headless.contains("true")) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                //chromeOptions.addArguments("--window-size=1920,1080");
            }
            capabilities.setCapability(chromeOptions.CAPABILITY,chromeOptions);
         }else if(browser.contains("firefox")){
             capabilities.setBrowserName("firefox");
             FirefoxOptions firefoxOptions = new FirefoxOptions();
             if (headless.contains("true")) {
                firefoxOptions.addArguments("--headless");
                 firefoxOptions.addArguments("--disable-gpu");
                 //firefoxOptions.addArguments("--window-size=1920,1080");
             }
             capabilities.merge(firefoxOptions);
         }else{
            capabilities.setBrowserName("edge");
            EdgeOptions edgeOptions = new EdgeOptions();
            if (headless.contains("true")) {
                edgeOptions.addArguments("--headless");
            }
             capabilities.setCapability(edgeOptions.CAPABILITY,edgeOptions);
         }
         capabilities.setPlatform(Platform.WIN10);
        driverThread.set(new RemoteWebDriver(hub_url,capabilities));
        driverThread.get().manage().window().maximize();
        driverThread.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
         ITestContext context= Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("driver",driverThread.get());
        return driverThread.get();
    }
    public void setDriver(){
        driverThread.set(initDriver());
    }

    public WebDriver getDriver(){
        return driverThread.get();
    }
    public void removeDriver(){
        getDriver().quit();
        driverThread.remove();
    }
}
