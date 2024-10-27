package automationP.api.oAuth;

import base.PropReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class BuildCoursesRequest {
    private  String endpoint="https://rahulshettyacademy.com";
    private  String oAuth_resource="/oauthapi/oauth2/resourceOwner/token";

    public  Response getOAuth(String clientId, String client_secret,String grant_type, String scope){
        RestAssured.baseURI=endpoint;
        Response response=given().log().all().formParams("client_id",clientId)
                .formParams("client_secret",client_secret)
                .formParams("grant_type",grant_type)
                .formParams("scope",scope)
                .when().post(oAuth_resource);
        return  response;

    }
    public String getOAuthToken(){
        Response response=getOAuth(
                PropReader.getProp("client_id").toString(),
                PropReader.getProp("client_secret").toString(),
                PropReader.getProp("grant_type").toString(),
                PropReader.getProp("scope").toString()
        );

        response.then().assertThat().statusCode(200);
        return response.path("access_token");
    }

    private String coursesSource="/oauthapi/getCourseDetails";
    public Response getCourses(String access_token){
        return given().log().all().queryParams("access_token",access_token)
                .when().get(coursesSource);
    }
}
