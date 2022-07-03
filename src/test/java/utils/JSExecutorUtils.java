package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSExecutorUtils {
    public static JavascriptExecutor js;

    public static void jsNavigate(WebDriver driver,String url){
        js=(JavascriptExecutor) driver;
        String script = String.format("window.location = '%s'",url);
        js.executeScript(script);
    }

    public static void jsScroll(WebDriver driver){
        js=(JavascriptExecutor) driver;
        String script=String.format("window.scrollTo(0,document.body.scrollHeight)");
        js.executeScript(script);

    }
    public static void jsScroll(WebDriver driver, WebElement element){
        js=(JavascriptExecutor) driver;
        String scroll1=String.format("arguments[0].scrollIntoView(true);");
        js.executeScript(scroll1,element);
    }

    public static void jsScrollBy(WebDriver driver, int xAxis, int yAxis){
        js = (JavascriptExecutor) driver;
        String  sc = String.format("window.scrollBy(%d,%d)",xAxis,yAxis);
        js.executeScript(sc);
    }

    public static void jsScrollBy(WebDriver driver, WebElement element){
        js = (JavascriptExecutor) driver;
        Point location = element.getLocation();
        int xAxis=location.getX();
        int yAxis=location.getY();
        String  sc = String.format("window.scrollBy(%d,%d)",xAxis,yAxis);
        js.executeScript(sc);
    }





    public static void jsHighlight(WebDriver driver, WebElement targetElement){
        js = (JavascriptExecutor) driver;
        String  script2 = "arguments[0].style.border='2px solid green'";
        js.executeScript(script2,targetElement);

    }

    public static void  jsClick(WebDriver driver, WebElement button){
        js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",button);
    }

    public  static void jsSendKeys(WebDriver driver,WebElement element, String text){
        js=(JavascriptExecutor) driver;
        jsHighlight(driver,element);
        String script = String.format("arguments[0].value='%s'",text);
        js.executeScript(script,element);
    }





}
