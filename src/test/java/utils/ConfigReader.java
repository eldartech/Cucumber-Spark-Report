package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        String path="src/test/resources/configuration.properties";
        try{
            //line of code to open the file
            FileInputStream input = new FileInputStream(path);
            properties=new Properties();
            //line of code to load opened file
            properties.load(input);
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
