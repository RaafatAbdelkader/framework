package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count=0;
    int maxCount=1;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxCount){
            count++;
            return true;
        }else{
            return false;
        }

    }
}
