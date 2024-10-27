package automationP.testcases;

import automationP.pages.HomePage;
import automationP.pages.AllActions;
import base.*;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class   GUI extends Testbase{
String uploadedFilename="1729446602538_test";
    @Test( priority = 1,groups = "smoke")
    public void uploadFile() {
        HomePage homePage=new HomePage(driver);
        AllActions allActions =homePage.navigateTo("upload");
        allActions.uploadFile("C:\\t\\test.txt");
        allActions.submitFile();
        Assert.assertTrue(allActions.getUploadMsg().contains("File Uploaded!"),"File can not be uploaded");
        uploadedFilename=allActions.getUploadFilename();
    }

    @Test( priority = 1,groups = "smoke")
    public void downloadFile(){
        HomePage homePage=new HomePage(driver);
        AllActions allActions =homePage.navigateTo("download");
        allActions.downloadFile(uploadedFilename);
    }

    @Test( priority = 1,groups = "smoke")
    public void verifyTableData(){
        HomePage homePage=new HomePage(driver);
        AllActions allActions =homePage.navigateTo("table");
        Map <String,Map<String,String>> data=allActions.getTableData();
        data.forEach((key,value)->{
            System.out.println(key+": "+value.entrySet());
        });
        System.out.println("Mein Data: "+data.get("Chrome").get("CPU"));
    }








    @Test( priority = 1,retryAnalyzer = Retry.class)
    public void tc_2() throws InterruptedException {
        Thread.sleep(6000);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("psw")).sendKeys("1234");
        driver.findElement(By.id("submit")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//a[contains(@href,'add')]")).click();

        AllActions allActions =new AllActions(driver);
        Thread.sleep(6000);
    }
    @Test( priority = 1)
    public void tc_3() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        ResultSet result =dbConnect.executeQuery("select * from users");
        if (result != null){
            while (result.next()){
                String email=result.getString("email");
                exTest.get().log(Status.INFO,(email));
            }
          //  Assert.assertEquals(1,1);

        }
    }

    @Test( priority = 1)
    public void tc_4() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        ResultSet result =dbConnect.executeQuery("select * from users");
        if (result != null){
            while (result.next()){
                String email=result.getString("email");
                exTest.get().log(Status.INFO,(email));
            }
           // Assert.assertEquals(1,2);

        }

    }
    @Test( priority = 1)
    public void tc_5() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        ResultSet result =dbConnect.executeQuery("select * from users");
        if (result != null){
            while (result.next()){
                String email=result.getString("email");
                exTest.get().log(Status.INFO,(email));
            }

        }
    }
    @Test( priority = 1)
    public void tc_6() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        ResultSet result =dbConnect.executeQuery("select * from users");
        if (result != null){
            while (result.next()){
                String email=result.getString("email");
                exTest.get().log(Status.INFO,(email));
            }

        }
    }


}
