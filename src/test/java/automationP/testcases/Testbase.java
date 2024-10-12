package automationP.testcases;

import base.MyExtentReport;
import base.PropReader;
import base.SetupDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

public class Testbase {

    public ExtentReports report= MyExtentReport.config();
    public String url=PropReader.getProp("url").toString();
    public static ThreadLocal<ExtentTest>exTest=new ThreadLocal<>();
    public SetupDriver setupDriver=new SetupDriver();
    WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public void before(){

        setupDriver.setDriver();
        driver=(setupDriver.getDriver());
        driver.manage().window().maximize();
        ITestContext context= Reporter.getCurrentTestResult().getTestContext();
        context.setAttribute("driver",driver);
        context.setAttribute("report",report);
        exTest.set(report.createTest("Test"));
        context.setAttribute("exTest",exTest);
        exTest.get().log(Status.INFO, "Test started with Thread-ID: "+Thread.currentThread().getId());
        driver.get(url);
        exTest.get().log(Status.INFO,"navigated to: "+url);
    }

    @AfterMethod
    public  void  after(){
        setupDriver.removeDriver();
    }


}
