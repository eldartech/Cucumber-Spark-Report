package pages.com.openMRS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class OpenMRSLogInPage {
    public OpenMRSLogInPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "authUser")
    private WebElement userNameInput;

    @FindBy(id = "clearPass")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement logInButton;

    /**
     * Method is used to log in to OpenMRS.
     * It takes String username and String password.
     * @param username
     * @param password
     */
    public void logIn(String username,String password){
        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        logInButton.click();
    }
}
