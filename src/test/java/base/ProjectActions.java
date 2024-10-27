package base;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class ProjectActions {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    String downloadPath= PropReader.download_dir;
    public ProjectActions(WebDriver driver) {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        actions=new Actions(driver);
    }

    public void waitToBeVisible(WebElement e){
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public void scrollDToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean elementFound = false;
        int limit=0;
        while (!elementFound && limit<20) {
            try {
                removeAdds();
                wait.until(ExpectedConditions.elementToBeClickable(element));
                actions.moveToElement(element).build().perform();
                element.click();
                System.out.println("Element has been found");
                elementFound=true;

            }catch(Exception e){
                js.executeScript("window.scrollBy(0,500);");
                System.out.println("waiting....");
                limit++;
            }

        }
    }
    public void removeAdds(){
        String script = "var adSelectors = ['iframe', '.ad', '.ads', '.ad-banner', '.ad-container', '.ad-slot'];" +
                "adSelectors.forEach(function(selector) {" +
                "  var ads = document.querySelectorAll(selector);" +
                "  ads.forEach(function(ad) { ad.remove(); });" +
                "});" +
                "console.log('Ads removed');";
        String script2 = "var blockedUrls = ['ad.doubleclick.net', 'ads.google.com', 'googleads.g.doubleclick.net'];" +
                "var realFetch = window.fetch;" +
                "window.fetch = function() {" +
                "  var url = arguments[0];" +
                "  if (blockedUrls.some(adUrl => url.includes(adUrl))) {" +
                "    console.log('Blocked ad: ' + url);" +
                "    return new Promise((resolve, reject) => {});" +
                "  }" +
                "  return realFetch.apply(this, arguments);" +
                "};" +
                "var realXhrOpen = window.XMLHttpRequest.prototype.open;" +
                "window.XMLHttpRequest.prototype.open = function(method, url) {" +
                "  if (blockedUrls.some(adUrl => url.includes(adUrl))) {" +
                "    console.log('Blocked ad: ' + url);" +
                "    return;" +
                "  }" +
                "  return realXhrOpen.apply(this, arguments);" +
                "};";
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript(script);
       // js.executeScript(script2);
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

    public String getPDFContent(String filePath) {
        try {
            File file=new File(filePath);
            PDDocument pdDoc = Loader.loadPDF(file);
            String content=new PDFTextStripper().getText(pdDoc);
            pdDoc.close();
            return content;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }

    }

    private String lastTabId=null;
    public String openNewTab(){
        lastTabId=driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        System.out.println("switched to a new tab");
        return driver.getWindowHandle();
    }

    public void returnToLastTab(){
        if (lastTabId!=null) {
            driver.switchTo().window(lastTabId);
            System.out.println("switched back to the last tab");
        }else
            System.out.println("No last tab found");
    }
    public String getIdOfCurrentTab(){
        return  driver.getWindowHandle();
    }
    public String[] getAllFilesName() {

        File dir = new File(downloadPath);
        Collection<String> files =new ArrayList<>();
        if(dir.isDirectory()){
            File[] listFiles = dir.listFiles();
            for(File file : listFiles){
                if(file.isFile()) {
                    files.add(file.getName());
                }
            }
        }
        return files.toArray(new String[]{});
    }
    public File  getLastModifiedFile() {
        File dir = new File(downloadPath);
        File lastModifiedFile = null;
        if(dir.isDirectory()&&dir.listFiles().length>0){
            File[] listFiles = dir.listFiles();
            lastModifiedFile=listFiles[0];
            for (int i = 1; i < listFiles.length; i++) {
                if (lastModifiedFile.lastModified()<listFiles[i].lastModified()) {
                    lastModifiedFile=listFiles[i];
                }
            }
        }else
            System.out.println("No file was found");

        return lastModifiedFile;
    }
    public void deleteLastModifiedFile(){
        File lastModifiedFile=getLastModifiedFile();
        String path=lastModifiedFile.getPath();
        if (lastModifiedFile.exists()&lastModifiedFile.isFile()) {
            if (lastModifiedFile.delete()) {
                System.out.println("File deleted"+path);
            }else
                System.out.println("File cant be deleted"+path);
        }
    }
    public void openLastModifiedFile(){
        File fl=getLastModifiedFile();
        if (fl!=null&& fl.isFile()) {
            driver.navigate().to(fl.getPath());
            System.out.println("opened the last modified file: "+fl.getPath());
        }
    }
    public void cleanupProjectDownLoadDir(){
        File dir = new File(downloadPath);
        if(dir.isDirectory()&&dir.listFiles().length>0){
            File[]files=  dir.listFiles();
            for (File file:files) {
                if (file.isFile())
                    file.delete();
                System.out.println("Deleted file: " + file.getPath());
            }
        }

    }
    public Integer getNumOfFilesExist(){
        File dir = new File(downloadPath);
        if (dir.isDirectory()& dir.exists())
            return dir.listFiles().length;
        else
            return 0;
    }
    public void waitToBeDownloaded(){
        int tabs= driver.getWindowHandles().size();
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabs-1));
    }

}
