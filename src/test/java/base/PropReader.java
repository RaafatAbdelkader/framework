package base;

import java.io.FileInputStream;
import java.util.Properties;

public class PropReader {
    static String  filepath="src/test/resources/prop.properties";
    static  String download_dir= System.getProperty("user.dir")+"\\download";
    public static Object getProp(String key){
        //test -Psmoke -Dbrowser=chrome
        if (System.getProperty(key)!=null){
            return System.getProperty(key);
        }else {
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

}
