package steps.com.webOrder;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.com.webOrder.WebOrderEditOrderPage;
import pages.com.webOrder.WebOrderHomePage;
import utils.ElementUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static utils.Driver.getReference;
import static utils.ElementUtils.getTexts;

public class WebOrderHomePageStep {
    WebOrderHomePage webOrderHomePage=new WebOrderHomePage(getReference());
    WebOrderEditOrderPage webOrderEditOrderPage=new WebOrderEditOrderPage(getReference());


    @And("user navigates to View all products tab")
    public void userNavigatesToViewAllProductsTab() {
        webOrderHomePage.viewAllProducts.click();
    }

    @Then("user validates the product details table")
    public void userValidatesTheProductDetailsTable(DataTable dataTable) {
        List<String> actualDetails = getTexts(webOrderHomePage.listOfProducts);
//        List<String> expectedProducts= Arrays.asList("Product name","Price","Discount","MyMoney","$100","8%",
//                "FamilyAlbum","$80","15%","ScreenSaver","$20","10%");
        for (int i = 0; i < actualDetails.size(); i++) {
            Assert.assertEquals("Detail test failed", dataTable.asList().get(i), actualDetails.get(i));
        }

    }

    @When("user clicks on orders")
    public void userClicksOnOrders() {
        webOrderHomePage.orders.click();
    }

    @And("user enters product info {string} and quantity of {string}")
    public void userEntersProductInfoAndQuantityOf(String product, String quantity) {
        Select select=new Select(webOrderHomePage.product);
        select.selectByVisibleText(product);
        webOrderHomePage.quantity.clear();
        webOrderHomePage.quantity.sendKeys(quantity);
    }

    @And("user enters address info {string}, {string},{string},{string},{string}")
    public void userEntersAddressInfo(String customerName, String street, String city, String state, String zipCode) {
        webOrderHomePage.customerName.sendKeys(customerName);
        webOrderHomePage.street.sendKeys(street);
        webOrderHomePage.city.sendKeys(city);
        webOrderHomePage.state.sendKeys(state);
        webOrderHomePage.zipCode.sendKeys(zipCode);
    }

    @And("user enters payment info {string}, {string},{string}")
    public void userEntersPaymentInfo(String cardType, String cardNum, String expirationDate) {
        for (WebElement card:webOrderHomePage.cardTypes) {
            if (card.getAttribute("value").equalsIgnoreCase(cardType)){
                card.click();
            }
        }

        webOrderHomePage.cardNumber.sendKeys(cardNum);
        webOrderHomePage.expirationDate.sendKeys(expirationDate);
        webOrderHomePage.processButton.click();

    }

    @Then("user validates success message of {string}")
    public void userValidatesSuccessMessageOf(String successMessage) {

        Assert.assertEquals("New Order was not created.",successMessage,webOrderHomePage.successMessage.getText());
        Assert.assertTrue(successMessage,webOrderHomePage.successMessage.isDisplayed());
    }

    @And("user validates url for all products")
    public void userValidatesUrlForAllProducts() {
        Assert.assertTrue(getReference().getCurrentUrl().contains("weborders/Products"));
    }

    @Then("user validates content headers")
    public void user_validates_content_headers(DataTable dataTable) {
        Assert.assertEquals("Header Test Failed",dataTable.asList(),getTexts(webOrderHomePage.headers));

    }

    @Then("user validates labels for Product Information")
    public void userValidatesLabelsForProductInformation(DataTable dataTable) {
        List<String> expectedLabels=dataTable.asList();
        List<String> actualLabels=getTexts(webOrderHomePage.prodInfoLabels);
        Assert.assertEquals("Prod Info Test Failed",expectedLabels,actualLabels);
    }

    @And("user validates labels for Address Information")
    public void userValidatesLabelsForAddressInformation(List<String> expectedLabels) {
        Assert.assertEquals("Address Info Test Failed",expectedLabels,getTexts(webOrderHomePage.addressInfoLabels));
    }

    @And("user validates labels for Payment Information")
    public void userValidatesLabelsForPaymentInformation(DataTable dataTable) {
        Assert.assertEquals("Payment Info Test Failed",dataTable.asList(),getTexts(webOrderHomePage.payInfoLabels));
    }

    @When("user edits first order from list of all orders")
    public void userEditsFirstOrderFromListOfAllOrders() {
        webOrderHomePage.firstOrderDetails.get(webOrderHomePage.firstOrderDetails.size()-1).click();
    }

    @And("user updates address info {string}, {string},{string},{string},{string}")
    public void userUpdatesAddressInfo(String name, String street, String city, String state, String zip) {
        webOrderEditOrderPage.customerName.clear();
        webOrderEditOrderPage.customerName.sendKeys(name);
        webOrderEditOrderPage.street.clear();
        webOrderEditOrderPage.street.sendKeys(street);
        webOrderEditOrderPage.city.clear();
        webOrderEditOrderPage.city.sendKeys(city);
        webOrderEditOrderPage.state.clear();
        webOrderEditOrderPage.state.sendKeys(state);
        webOrderEditOrderPage.zipCode.clear();
        webOrderEditOrderPage.zipCode.sendKeys(zip);
    }

    @And("user updates payment info {string}, {string},{string}")
    public void userUpdatesPaymentInfo(String cardType, String cardNumber, String expDate) {
        ElementUtils.click(webOrderEditOrderPage.cardTypes,"value",cardType);
        webOrderEditOrderPage.cardNumber.clear();
        webOrderEditOrderPage.cardNumber.sendKeys(cardNumber);
        webOrderEditOrderPage.expirationDate.clear();
        webOrderEditOrderPage.expirationDate.sendKeys(expDate);
    }

    @And("user clicks update button")
    public void userClicksUpdateButton() {
        webOrderEditOrderPage.updateButton.click();
    }

    @Then("user validates updated information {string}, {string},{string},{string},{string}, {string}, {string},{string}")
    public void userValidatesUpdatedInformation(String name, String street, String city, String state, String zip,String cardType, String cardNumber, String expDate) {
    List<String> actualUpdatedOrder = ElementUtils.getTexts(webOrderHomePage.firstOrderDetails);
    List<String> expectedOrderDetails = List.of(name,street,city,state,zip,cardType,cardNumber,expDate);
    for (int i=0; i<expectedOrderDetails.size();i++){
        Assert.assertTrue("Order Details Test Failed",actualUpdatedOrder.contains(expectedOrderDetails.get(i)));
    }

    }

    @And("user navigates to {string} tab")
    public void userNavigatesToTab(String pageName) {
        ElementUtils.click(webOrderHomePage.pages,pageName);
    }



    @Then("user validates created information {string}, {string},{string}, {string},{string},{string},{string}, {string}, {string},{string}")
    public void userValidatesCreatedInformation(String product, String quantity, String name, String street, String city, String state, String zip,String cardType, String cardNumber, String expDate) {
        List<String> actualCreatedOrder = ElementUtils.getTexts(webOrderHomePage.firstOrderDetails);
        List<String> expectedOrderDetails = List.of(product,quantity,name,street,city,state,zip,cardType,cardNumber,expDate);
        for (int i=0; i<expectedOrderDetails.size();i++){
            Assert.assertTrue("Order Details Test Failed",actualCreatedOrder.contains(expectedOrderDetails.get(i)));
        }
    }

    @When("user extracts all orders to excel sheet {string}")
    public void userExtractsAllOrdersToExcelSheet(String sheet) throws IOException {
        String path ="/Users/techtorialacademy/IdeaProjects/Cucumber-Spark-Report/src/test/resources/data/WebOrderList.xlsx";
        File excelFile = new File(path);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet1 = workbook.getSheet(sheet);
        Row row = sheet1.createRow(0);
        for (int i=0;i<webOrderHomePage.columnNames.size();i++){
            row.createCell(i);
            row.getCell(i).setCellValue(webOrderHomePage.columnNames.get(i).getText());
        }

        for (int r=2,i=1;i<webOrderHomePage.rowData.size();r++,i++){
            Row row1 = sheet1.createRow(i);//3
            for (int c=0;c<webOrderHomePage.getCellDataForRow(r).size();c++){//r=4
                row1.createCell(c);
                row1.getCell(c).setCellValue(webOrderHomePage.getCellDataForRow(r).get(c).getText());
            }
        }


        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();

    }

    @Then("user validates last record in excel sheet")
    public void userValidatesLastRecordInExcelSheet() throws IOException {
        String path ="/Users/techtorialacademy/IdeaProjects/Cucumber-Spark-Report/src/test/resources/data/WebOrderList.xlsx";
        File excelFile = new File(path);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet1 = workbook.getSheet("Sheet1");

        Assert.assertEquals("Clare Jefferson",sheet1.getRow(sheet1.getLastRowNum()).getCell(0).toString());

    }
}