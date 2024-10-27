package automationP.api.oAuth;

import base.PropReader;
import io.restassured.response.Response;

public class Test {

    @org.testng.annotations.Test
    public void getCourses(){
        BuildCoursesRequest courses=new BuildCoursesRequest();
        String token=courses.getOAuthToken();
        Response response=courses.getCourses(token);
        response.then().assertThat().statusCode(401);
        response.prettyPrint();
    }
}
