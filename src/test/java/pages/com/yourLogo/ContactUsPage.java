package pages.com.yourLogo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {
    public ContactUsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(tagName = "select")
    private WebElement subjectHeadingSelect;

    @FindBy(id = "email")
    private  WebElement emailInput;

    @FindBy(id = "id_order")
    private WebElement orderInput;

    @FindBy(id = "message")
    private WebElement messageTextArea;

    @FindBy(id = "submitMessage")
    private WebElement sendButton;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    public WebElement successMessage;


    public void fillContactUsForm(String subjectHeading, String email, String order,String message){
        Select select=new Select(subjectHeadingSelect);
        select.selectByVisibleText(subjectHeading);

        emailInput.sendKeys(email);
        orderInput.sendKeys(order);
        messageTextArea.sendKeys(message);
    }

    public void clickSendButton(){
        sendButton.click();
    }

}
