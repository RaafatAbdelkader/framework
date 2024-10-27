package automationP.api.library;

import base.JsonParser;
import base.PropReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.*;

public class BuildRequest {
    static String endPoint= PropReader.getProp("library_endPoint").toString();
    static String resource;

    public Response addBook( String bookName, String isbn,String aisle,String author){
        RestAssured.baseURI=endPoint;
        resource="/Library/Addbook.php";
        //bodyBuild
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("name",bookName);
        jsonObject.put("isbn",isbn);
        jsonObject.put("aisle",aisle);
        jsonObject.put("author",author);
        String filepath=JsonParser.createJsonFile("request_addbook_"+bookName,jsonObject.toString());
        //headerBuild
        Header header= new Header("Content-Type","application/json");

        //getting body file
        String body =JsonParser.getJsonContent(filepath).toString();

        return given().header(header).body(body)
                .when().post(resource);
    }
}
