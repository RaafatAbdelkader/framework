package automationP.api.jira;

import base.JsonParser;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;

public class Test {

    @org.testng.annotations.Test(dataProvider = "getBody")
    public void  createBug(String body,String screenshot){
        BuildJiraRequest buildRequest=new BuildJiraRequest();
        Response response=buildRequest.createBug(body);
        response.then().assertThat().statusCode(201);
        String bugId=response.path("id");
        JsonParser.createJsonFile("Jira_CreateIssue_response",response.asPrettyString());
        Response response_attachment= buildRequest.attachFile(screenshot,bugId);
        response_attachment.then().assertThat().statusCode(200);
        JsonParser.createJsonFile("Jira_attachment_response",response_attachment.asPrettyString());
    }

    @DataProvider
    public Object[][] getBody(){
        Object [][]obj=new Object[1][2];
        obj[0][0]="{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"SCRUM\"\n" +
                "       },\n" +
                "       \"summary\": \"Login Seite geht nicht\",\n" +
                "       \"description\": \"Creating of an issue using progggject keys and issue type names using the REST API\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "   }\n" +
                "}\n";
        obj[0][1]=System.getProperty("user.dir")+"/screenshots/failed/tc_1_1729332865249.png";;
        return obj;

    }


}
