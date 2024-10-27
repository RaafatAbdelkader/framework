package automationP.api.jira;

import base.PropReader;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class BuildJiraRequest {
    private String token= PropReader.getProp("jiraT").toString();
    private String endpoint=PropReader.getProp("jira_endPoint").toString();
    String resource_createBug="/rest/api/2/issue/";


    @Test
    public Response createBug(String body){
        RestAssured.baseURI=endpoint;
        return given()
        .header("Authorization",token)
        .header("Content-Type","application/json")
        .body(body)
        .when().post(resource_createBug);

    }

    public Response attachFile(String filePath,String issueId){
        RestAssured.baseURI=endpoint;
        return given()
                .header("Authorization",token).pathParam("issueId",issueId)
                .header("Content-Type","multipart/form-data; boundary=<calculated when request is sent>")
                .header("X-Atlassian-Token","no-check")
                .multiPart(new File(filePath))
                .when().post("/rest/api/3/issue/{issueId}/attachments");
    }
}
