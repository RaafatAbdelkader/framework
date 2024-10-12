package base;

import java.io.FileInputStream;
import java.util.Properties;

public class PropReader {
    static String  filepath="src/test/resources/prop.properties";
    public static Object getProp(String key){
        FileInputStream fileInputStream= null;
        Properties properties= null;
        try {
            fileInputStream = new FileInputStream(filepath);
            properties =new Properties();
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.get(key);
    }

}
