package automationP.pages;

import base.ProjectActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;
    ProjectActions projectActions;
    public HomePage(WebDriver driver){
        this.driver=driver;
        projectActions=new ProjectActions(driver);
    }

    public AllActions navigateTo(String page){
        By pageButton=By.xpath("//a[contains(@href,'"+page+"')]");
        WebElement pageB=driver.findElement(pageButton);
        projectActions.scrollDToElement(pageB);
        projectActions.removeAdds();
        return new AllActions(driver);
    }


}
