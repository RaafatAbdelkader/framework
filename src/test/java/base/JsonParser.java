package base;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonParser {

    static  String jsonDir=System.getProperty("user.dir")+"/files/";

    public  static String createJsonFile(String filename, String content){
        filename= jsonDir+filename+"_"+System.currentTimeMillis()+".json";
        try {
            File file =new File(filename);
            file.getParentFile().mkdir();
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
            return file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public  static JSONObject getJsonContent(String filePah){
        try {
            String content= Files.readString(Path.of(filePah));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }




//    @Test
//    public void  test(){
//          JSONObject jsonObject =new JSONObject();
//
//          jsonObject.put("Users",new JSONObject());
//          jsonObject.put("admins",new JSONObject());
//          jsonObject.getJSONObject("admins").put("id",1);
//          jsonObject.getJSONObject("admins").put("email","gmail@hsb");
//
//          JsonPath jsonPath= new JsonPath(jsonObject.toString());
//
//          System.out.println(jsonPath.get("admins.email").toString());
//    }
}
