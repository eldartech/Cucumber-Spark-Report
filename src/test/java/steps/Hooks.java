package steps;

import com.mongodb.MapReduceCommand;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Driver;

public class Hooks {
    @Before("@UI")
    public void setUp(){
        Driver.getDriver();
    }

    @After("@UI")
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            final byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","image");
        }
    }
}
