package pages.com.openMRS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenMRSHomePage {
    public OpenMRSHomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "user_icon")
    public WebElement userIcon;

    @FindBy(css = "ul#userdropdown li.menuLabel span.font-weight-bold")
    public WebElement loggedInUser;
}
