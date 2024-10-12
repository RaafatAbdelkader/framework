package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyExtentReport {
  static   String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
  static String filepath= System.getProperty("user.dir")+"/reports/Extent_Report_"+date+".html";
  static  String browser= PropReader.getProp("browser").toString();
  public static ExtentReports config(){
    ExtentReports extentReport =new ExtentReports();
    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filepath);
    extentSparkReporter.config().setReportName("Report of: "+date);
    extentReport.attachReporter(extentSparkReporter);
    extentReport.setSystemInfo("Browser",browser);
    return extentReport;

  }


}
