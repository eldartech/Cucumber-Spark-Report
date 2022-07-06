package steps.com.webOrder;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.com.webOrder.WebOrderHomePage;
import pages.com.webOrder.WebOrderLoginPage;
import utils.ConfigReader;

import static utils.Driver.getReference;

public class WebOrderLoginStep {
    WebDriver driver;
    WebOrderLoginPage webOrderLogin;
    WebOrderHomePage webOrderHome;
    @Given("user in WebOrder")
    public void userInWebOrder() {
        driver=getReference();
        webOrderLogin=new WebOrderLoginPage(driver);
        driver.get(ConfigReader.getProperty("webOrderURL"));
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        webOrderLogin.username.sendKeys(username);
    }

    @And("user enters password {string}")
    public void userEntersPassword(String password) {
        webOrderLogin.password.sendKeys(password);
        webOrderLogin.loginButton.click();
    }

    @Then("user validates the title is {string}")
    public void userValidatesTheTitleIs(String title) {
        Assert.assertEquals("Title Test Failed",title,driver.getTitle());
    }

    @And("user validates the url contains {string}")
    public void userValidatesTheUrlContains(String parameter) {
        Assert.assertTrue("URL Test Failed",driver.getCurrentUrl().contains(parameter));
    }

    @And("user validates login info")
    public void userValidatesLoginInfo() {
        webOrderHome=new WebOrderHomePage(driver);
        Assert.assertTrue("Log in info test failed",webOrderHome.loginInfo.getText().contains("Tester"));
    }

    @Then("user validates the error message {string}")
    public void userValidatesTheErrorMessage(String message) {
        Assert.assertEquals("Error message test failed.",message,webOrderLogin.errorText.getText());
    }

    @And("user clicks on login button")
    public void userClicksOnLoginButton() {
        webOrderLogin.loginButton.click();
    }


    @When("user logs in with {string} username and {string} password")
    public void userLogsInWithUsernameAndPassword(String userName, String password) {
        webOrderLogin.logIn(userName,password);
    }

}
