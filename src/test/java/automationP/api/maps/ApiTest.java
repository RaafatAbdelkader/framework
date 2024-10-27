package automationP.api.maps;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    public String placeId;
    public String address="wolfgang str 107";
    String body= MapsPayload.buildPostPlaceBody(
            111.05,
            15,
            20,
            "Test",
            "01028743586",
            address,
            Arrays.asList("type1","type2"),
            "www.google.com",
            "deutsch");
    //@BeforeTest
    public  void  addPlace(){
        MapsWS maps=new MapsWS();
        Map<String,Object>queryParams=new HashMap<>();
        queryParams.put("key","qaclick123");
        maps.setQueryParams(queryParams);
        maps.setHeader(new Header("Content-Type","application/json"));
        maps.setBody(body);
        Response response=maps.addPlace();
        response.then().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server","Apache/2.4.52 (Ubuntu)")
                .header("Connection","Keep-Alive");
        placeId=response.path("place_id");
        //response.prettyPrint();
    }


    @Test()
    public void getPlace(){
        MapsWS maps=new MapsWS();
        Map<String,Object>queryParams=new HashMap<>();
        queryParams.put("key","qaclick123");
        queryParams.put("place_id",placeId);
        maps.setQueryParams(queryParams);
        maps.setSource("/maps/api/place/get/json");
        Response response=maps.getPlace();
        response.then().assertThat().statusCode(200)
                .body("name",equalTo("Test"))
                .header("server","Apache/2.4.52 (Ubuntu)")
                .header("Connection","Keep-Alive");

        //option 1
        Assert.assertEquals(response.jsonPath().get("location.latitude"),"111.05");

        //option 2
        JsonPath jsonpath =new JsonPath(response.asPrettyString());
        Assert.assertEquals(jsonpath.get("address"),address);


        //jsonpath.prettyPrint();
    }
    String neueAddress="Nue Str 198";
    @Test()
    public void updatePlace(){
        body=MapsPayload.buildPutPlaceBody(placeId,neueAddress,"qaclick123");
        MapsWS maps=new MapsWS();
        Map<String,Object>queryParams=new HashMap<>();
        queryParams.put("key","qaclick123");
        maps.setQueryParams(queryParams);
        maps.setSource("/maps/api/place/update/json");
        maps.setBody(body);
        maps.setHeader(new Header("Content-Type","application/json"));
        Response response=maps.putPlace();
        response.then().assertThat().statusCode(200)
                .header("server","Apache/2.4.52 (Ubuntu)")
                .header("Connection","Keep-Alive");
        Assert.assertEquals(response.jsonPath().get("msg"),"Address successfully updated");
        response.prettyPrint();
    }
    @Test(dependsOnMethods = "updatePlace")
    public void getPlaceAfterUpdate(){
        MapsWS maps=new MapsWS();
        Map<String,Object>queryParams=new HashMap<>();
        queryParams.put("key","qaclick123");
        queryParams.put("place_id",placeId);
        maps.setQueryParams(queryParams);
        maps.setSource("/maps/api/place/get/json");
        maps.setHeader(new Header("Content-Type","application/json"));
        Response response=maps.getPlace();
        response.then().assertThat().statusCode(200)
                .body("name",equalTo("Test"))
                .header("server","Apache/2.4.52 (Ubuntu)")
                .header("Connection","Keep-Alive");
        //option 1
        Assert.assertEquals(response.jsonPath().get("location.latitude"),"111.05");
        //option 2
        JsonPath jsonpath =new JsonPath(response.asPrettyString());
        Assert.assertEquals(jsonpath.get("address"),neueAddress);
        jsonpath.prettyPrint();
    }
}
