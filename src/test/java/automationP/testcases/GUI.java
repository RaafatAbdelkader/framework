package automationP.testcases;

import base.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class   GUI extends Testbase{

    @Test( priority = 1,groups = "smoke",retryAnalyzer = Retry.class)
    public void tc_1() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        ResultSet result =dbConnect.executeQuery("select * from users");
        if (result != null){
            while (result.next()){
                String email=result.getString("email");
                exTest.get().log(Status.INFO,(email));
        }
        Assert.assertEquals(1,2);

        }
    }

    @Test( priority = 1)
    public void tc_2() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        ResultSet result =dbConnect.executeQuery("select * from users");
        if (result != null){
            while (result.next()){
                String email=result.getString("email");
                exTest.get().log(Status.INFO,(email));
            }
            //Assert.assertEquals(1,2);

        }
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
