package pages.com.webOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebOrderHomePage {
    WebDriver driver;
    public WebOrderHomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "login_info")
    public WebElement loginInfo;

    @FindBy(xpath = "//td//ul/li")
    public List<WebElement> pages;

    //View All Orders
    @FindBy(xpath = "//table[@class='SampleTable']//tr[2]/td")
    public List<WebElement> firstOrderDetails;

    //View All Products
    @FindBy(linkText = "View all products")
    public WebElement viewAllProducts;

    @FindBy(xpath = "//table[@class='ProductsTable']//tr//*")
    public List<WebElement> listOfProducts;



    //Orders
    @FindBy(xpath = "//a[text()='Order']")
    public WebElement orders;

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement product;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantity;

    @FindBy(id= "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerName;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    public WebElement street;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    public WebElement city;

    @FindBy(id="ctl00_MainContent_fmwOrder_TextBox4")
    public WebElement state;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    public WebElement zipCode;

    @FindBy(xpath = "//input[@name='ctl00$MainContent$fmwOrder$cardList']")
    public List<WebElement> cardTypes;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumber;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expirationDate;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(tagName = "strong")
    public WebElement successMessage;

    @FindBy(tagName = "h3")
    public List<WebElement> headers;

    @FindBy(xpath = "//*[@id='ctl00_MainContent_fmwOrder']//td/ol[1]/li/label")
    public List<WebElement> prodInfoLabels;

    @FindBy(xpath = "//*[@id='ctl00_MainContent_fmwOrder']//td/ol[2]/li/label")
    public List<WebElement> addressInfoLabels;

    @FindBy(xpath = "//*[@id='ctl00_MainContent_fmwOrder']//td/ol[3]/li/label")
    public List<WebElement> payInfoLabels;


    @FindBy(xpath = "(//table[@class='SampleTable']/tbody/tr/th)[position()>1 and position()<last()]")
    public List<WebElement> columnNames;


    @FindBy(xpath = "//table[@class='SampleTable']/tbody/tr")
    public List<WebElement> rowData;


    public List<WebElement> getCellDataForRow(int row){
        return driver.findElements(
                By.xpath(String.format("//table[@class='SampleTable']/tbody/tr[%d]/td[position()>1 and position()<last()]",row)));
    }









}
