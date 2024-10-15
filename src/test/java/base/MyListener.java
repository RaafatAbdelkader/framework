package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener
{
    ExtentReports report;
    ThreadLocal<ExtentTest>exTest;
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        report = (ExtentReports) result.getTestContext().getAttribute("report");
        exTest= (ThreadLocal)result.getTestContext().getAttribute("exTest");
        exTest.get().getModel().setName(result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        exTest.get().log(Status.PASS,"Passed");
        SetupDriver setupDriver =new SetupDriver();
        WebDriver driver = setupDriver.getDriver();
        if (driver!= null){
            setupDriver.removeDriver();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        String name= result.getMethod().getMethodName();
        SetupDriver setupDriver =new SetupDriver();
        WebDriver driver = setupDriver.getDriver();
        if (driver!= null){
            ProjectActions actions = new ProjectActions(driver);
            String filepath=actions.take_scr_shot(name);
            exTest.get().addScreenCaptureFromPath(filepath);
            exTest.get().fail(result.getThrowable());
            setupDriver.removeDriver();

        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        exTest.get().skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);

    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("Report has been saved to: "+MyExtentReport.filepath);
        ExtentReports report= (ExtentReports)context.getAttribute("report");
        report.flush();
    }
}
