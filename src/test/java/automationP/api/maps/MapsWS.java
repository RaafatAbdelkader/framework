package automationP.api.maps;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; //must be imported manually

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MapsWS {
    //given--> all inputs / Body / Header / parameters
    //when --> Methode(Post/Get/Put/delete) - Source
    //then --> output
    String endpoint="https://rahulshettyacademy.com";
    String source="/maps/api/place/add/json";

    public void setSource(String source) {
        this.source = source;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    String body=null;
    Map<String,Object> queryParams =new HashMap();
    Header header =null;



    public Response addPlace(){
        RestAssured.baseURI=endpoint;
        Response response=given().log().all().queryParams(queryParams).body(body).header(header)
                .when().post(source);
        return response;
    }
    public Response getPlace(){
        RestAssured.baseURI=endpoint;
        Response response=given().log().headers().queryParams(queryParams)
                .when().get(source);
        return response;
    }
    public Response putPlace(){
        RestAssured.baseURI=endpoint;
        Response response=given().log().headers().queryParams(queryParams).header(header).body(body)
                .when().put(source);
        return response;
    }
}
