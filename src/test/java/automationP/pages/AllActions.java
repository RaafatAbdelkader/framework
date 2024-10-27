package automationP.pages;

import base.ProjectActions;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class AllActions {
    WebDriver driver;
    ProjectActions actions;
    public AllActions(WebDriver driver){
        this.driver=driver;
        actions=new ProjectActions(driver);
        actions.removeAdds();
    }
    By uploadInput= By.xpath("//input[@type='file']");
    public void uploadFile(String filePath){
        driver.findElement(uploadInput).sendKeys(filePath);
    }


    By submitFile=By.id("fileSubmit");
    public void submitFile(){
        actions.removeAdds();
        actions.scrollDToElement(driver.findElement(submitFile));
    }
    By uploadMsg=By.xpath("//div[@id='uploaded-files']/parent::div //h1");

    public  String getUploadMsg(){
        WebElement e =driver.findElement(uploadMsg);
        actions.waitToBeVisible(e);
        actions.removeAdds();
        return e.getText();
    }
    By filename=By.xpath("//div[@id='uploaded-files']/p");

    public  String getUploadFilename(){
        WebElement e =driver.findElement(filename);
        actions.waitToBeVisible(e);
        actions.removeAdds();
        return e.getText().trim();
    }
    public void downloadFile(String filename){
        By downloadFile=By.xpath("//a[contains(@href,'"+filename+"')]");
        WebElement e=driver.findElement(downloadFile);
        int num=actions.getNumOfFilesExist();
        actions.removeAdds();
        actions.scrollDToElement(e);
        int newNum=actions.getNumOfFilesExist();
        for (int i = 0; i < 10 && num==newNum; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        actions.openLastModifiedFile();
    }

    By tHeader= By.xpath("//thead/tr/th");
    By tBodyRows= By.xpath("//tbody/tr");
    public Map<String,Map<String,String>> getTableData(){
        Map<String,Map<String,String>> tableData=new HashMap<>();
        List<WebElement> headerValues=driver.findElements(tHeader);
        List<WebElement> rows=driver.findElements(tBodyRows);
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells=rows.get(i).findElements(By.tagName("td"));
            Map<String,String>cellValues=new HashMap<>();
            for (int j = 0; j < cells.size(); j++) {
                cellValues.put(headerValues.get(j).getText(),
                        cells.get(j).getText()) ;
                tableData.put(cells.get(0).getText(),cellValues);
            }

        }

        return tableData;

    }
}
