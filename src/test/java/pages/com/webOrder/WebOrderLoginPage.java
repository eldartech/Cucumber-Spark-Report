package pages.com.webOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrderLoginPage {
    public WebOrderLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(name = "ctl00$MainContent$username")
    public WebElement username;

    @FindBy(name = "ctl00$MainContent$password")
    public WebElement password;

    @FindBy(name = "ctl00$MainContent$login_button")
    public WebElement loginButton;

    @FindBy(id = "ctl00_MainContent_status")
    public WebElement errorText;

    public void logIn(String userName, String password){
        username.sendKeys(userName);
        this.password.sendKeys(password);
        loginButton.click();
    }
}
