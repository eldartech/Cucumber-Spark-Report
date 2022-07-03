package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class BrowserUtils {
    /**
     * switchWindowByTitle is switching Window based on provided title.
     * @param driver
     * @param title
     */
    public static void switchWindowByTitle(WebDriver driver,String title){
        Set<String> ids = driver.getWindowHandles();
        for (String id:ids){
            if (!driver.getTitle().equals(title)){
                driver.switchTo().window(id);
            }
        }
    }

    public static void switchWindowByURL(WebDriver driver,String url){
        Set<String> ids = driver.getWindowHandles();
        for (String id:ids){
            if (driver.getCurrentUrl().contains(url)){
                driver.switchTo().window(id);
            }
        }
    }

 /*   public static void switchWindowByURL(WebDriver driver, String parentUrl){
        Set<String> ids = driver.getWindowHandles();
        for (String id:ids){
            if (!driver.getCurrentUrl().equals(parentUrl.toString())){
                driver.switchTo().window(id);
            }
        }
    }*/

    public static void closeWindows(WebDriver driver){
        String parentID = driver.getWindowHandle();
        Set<String> ids =  driver.getWindowHandles();
        for (String id: ids){
            if (!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }

    public static void closeWindows(WebDriver driver, String parentID){
        Set<String> ids =  driver.getWindowHandles();
        for (String id: ids){
            if (!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }


}
