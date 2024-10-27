package automationP.api.library;

import base.JsonParser;
import io.restassured.response.Response;
import org.testng.annotations.*;

public class Test {
    @org.testng.annotations.Test(dataProvider = "getBookData")
    public void addBook(String bookname,String isbn, String aisle,String author){
        BuildRequest buildRequest =new BuildRequest();
        Response response =buildRequest.addBook(bookname,isbn,aisle,author);
        response.then().assertThat().statusCode(200);
        JsonParser.createJsonFile("response_addBook_testbook",response.asPrettyString());
    }

    @DataProvider
    public Object [][] getBookData(){
        Object [][] obj =new Object[3][4];

        obj[0][0]="1 book";
        obj[0][1]="isbn";
        obj[0][2]="aisb";
        obj[0][3]="Raaafat Abdelkader";

        obj[1][0]="2 Book";
        obj[1][1]="dfjd";
        obj[1][2]="dfjd";
        obj[1][3]="Mohamed";

        obj[2][0]="3 Book";
        obj[2][1]="anti";
        obj[2][2]="aflok";
        obj[2][3]="Roki";

    return obj;
    }
}
