package steps.com.openMRS;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.com.openMRS.OpenMRSHomePage;
import pages.com.openMRS.OpenMRSLogInPage;
import utils.ConfigReader;
import utils.Driver;

import java.util.List;

public class OpenMRSLogInStep {
    WebDriver driver = Driver.getDriver();
    OpenMRSLogInPage logInPage= new OpenMRSLogInPage(driver);
    OpenMRSHomePage homePage=new OpenMRSHomePage(driver);
    @Given("user is in OpenMRS login page")
    public void userIsInOpenMRSLoginPage() {
        driver.get(ConfigReader.getProperty("openMrsURL"));
    }

    @When("user logs in with valid credentials")
    public void userLogsInWithValidCredentials(DataTable credentials) {
        String username=credentials.asList().get(0);
        String password=credentials.asList().get(1);
        logInPage.logIn(username,password);
    }

    @Then("user validates logged in user is {string}")
    public void userValidatesLoggedInUserIs(String expectedUserName) {
        homePage.userIcon.click();
        Assert.assertEquals("Logged in User Name Test Failed",expectedUserName,homePage.loggedInUser.getText());
    }
}
