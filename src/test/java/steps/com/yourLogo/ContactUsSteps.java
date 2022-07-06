package steps.com.yourLogo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.com.yourLogo.ContactUsPage;
import utils.ConfigReader;
import utils.Driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ContactUsSteps {
    WebDriver driver = Driver.getDriver();
    ContactUsPage contactUsPage=new ContactUsPage(driver);
    @Given("user navigates to contact us page")
    public void userNavigatesToContactUsPage() {
        driver.get(ConfigReader.getProperty("yourLogoContactUs"));
    }

    @When("user fills the form with given sheet name {string} and row number {int}")
    public void userFillsTheFormWithGivenSheetNameAndRowNumber(String sheetName, int rowNumber) throws IOException {
        String path = "/Users/techtorialacademy/Desktop/YourLogo.xlsx";
        File excelFile=new File(path);
        FileInputStream inputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet=workbook.getSheet(sheetName);
        Row row=sheet.getRow(rowNumber);

        String subjectHeading=row.getCell(0).toString();
        String email= row.getCell(1).toString();
        String order=row.getCell(2).toString();
        String message=row.getCell(3).toString();
        contactUsPage.fillContactUsForm(subjectHeading,email,order,message);
    }

    @And("user clicks on send button")
    public void userClicksOnSendButton() {
        contactUsPage.clickSendButton();
    }

    @Then("user validates successful message {string}")
    public void userValidatesSuccessfulMessage(String successMessage) {
        Assert.assertEquals("Success Message Test Failed",successMessage,contactUsPage.successMessage.getText());
    }
}
