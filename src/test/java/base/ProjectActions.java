package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ProjectActions {
    WebDriver driver;
    public ProjectActions(WebDriver driver) {
        this.driver=driver;
    }


    public String take_scr_shot(String name){
       long time=System.currentTimeMillis();
        String filePath=System.getProperty("user.dir")+"/screenshots/failed/"+name+"_"+time+".png";

        File file =new File(filePath);
        File pic=driver.findElement(By.tagName("Body")).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.moveFile(pic,file);
            return  filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return  e.getMessage();
        }

    }

}
