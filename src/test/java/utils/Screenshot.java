package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    public static void takeScreenshot(String scenario){
        File source = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"/target/screenshots/"+scenario+System.currentTimeMillis()+".png";
        File path = new File(destination);
        try{
            FileUtils.copyFile(source,path);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
