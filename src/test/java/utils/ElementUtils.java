package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.JSExecutorUtils.jsHighlight;
import static utils.JSExecutorUtils.jsScroll;

public class ElementUtils {
    //Text
    public static List<String> getTexts(List<WebElement> elements){
        List<String> texts = new ArrayList<>();
        for (int i=0;i<elements.size();i++){
            texts.add(elements.get(i).getText());
        }
        return texts;
    }

    //Page
    public static void pageDown(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    //Wait
    public static void sleep(int milisec) {
        try {
            Thread.sleep(milisec);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public static WebElement elementClickable(WebDriver driver,int timeInSec,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,timeInSec);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement visibilityOf(WebDriver driver, int timeInSec, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,timeInSec);
        return  wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement visibilityOfElementLocated(WebDriver driver, int timeInSec, By locator){
        WebDriverWait wait = new WebDriverWait(driver,timeInSec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement invisibilityOfElementLocated(WebDriver driver,int timeInSec,By locator){
        WebDriverWait wait = new WebDriverWait(driver,timeInSec);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public static WebElement fluentWait(WebDriver driver, long totalSec, long pollingSec, By locator){
        Wait<WebDriver> wait=new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(totalSec))
                .pollingEvery(Duration.ofSeconds(pollingSec))
                .ignoring(RuntimeException.class);
        return wait.until(element->element.findElement(locator));
    }

    //click
    public static void click(WebDriver driver,WebElement element){
        jsScroll(driver,element);
        jsHighlight(driver,element);
        elementClickable(driver,5,element).click();
    }


    public static void click(List<WebElement> elements, String text){
        Iterator<WebElement> iterator=elements.iterator();
        while (iterator.hasNext()){
            WebElement element=iterator.next();
            if (element.getText().equals(text)){
                element.click();
                break;
            }
        }

    }

    public static void click(List<WebElement> elements,String attribute, String text){
        Iterator<WebElement> iterator=elements.iterator();
        while (iterator.hasNext()){
            WebElement element=iterator.next();
            if (element.getAttribute(attribute).equals(text)){
                element.click();
                break;
            }
        }

    }























}
