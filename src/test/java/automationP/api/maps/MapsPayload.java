package automationP.api.maps;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MapsPayload {

    static double locationLat;
    static double locationLng;
    static int accuracy;
    static String name=null;
    static String phone_number=null;
    static ArrayList<String> types=new ArrayList<>();
    static String website=null;
    static String language=null;

    public static String buildPostPlaceBody(double locationLat, double locationLng, int accuracy, String name, String phone_number,String address
                                            ,List<String> types, String website, String language){
        String body="{\n" +
            "  \"location\": {\n" +
            "    \"lat\": "+locationLat+",\n" +
            "    \"lng\": "+locationLng+"\n" +
            "  },\n" +
            "  \"accuracy\": "+accuracy+",\n" +
            "  \"name\": \""+name+"\",\n" +
            "  \"phone_number\": \""+phone_number+"\",\n" +
            "  \"address\": \""+address+"\",\n" +
            "  \"types\": [\n";
            for (int i = 0; i < types.size(); i++) {
               body += "\""+types.get(i)+"\"";
                if(i!= types.size()-1){
                    body += ",\n";
                }else{
                    body += "\n";
                }
            }
            body+= "  ],\n" +
            "  \"website\": \""+website+"\",\n" +
            "  \"language\": \""+language+"\"\n" +
            "}";
            return body;
        }

    public static String buildPutPlaceBody( String place_id, String address,String key){
        String body="{\n" +
                "\"place_id\":\""+place_id+"\",\n" +
                "\"address\":\""+address+"\",\n" +
                "\"key\":\""+key+"\"\n" +
                "}\n";
        return body;
    }





}
