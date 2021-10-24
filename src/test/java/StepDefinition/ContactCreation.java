package StepDefinition;

import Pages.CommonPageFunctions;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class ContactCreation {
    WebDriver driver;
    String[] Productnames = new String[10];
    int prodcounter = 0;

    @Given("I Launch the browser")
    public void iEnterdetails() throws InterruptedException {
        String driverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", driverPath + "/src/test/resources/Driver/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("http://jupiter.cloud.planittesting.com");
        driver.manage().window().fullscreen();
        Assert.assertEquals(driver.getTitle(), "Jupiter Toys");
        System.out.println("Web browser Launched");
        Thread.sleep(3000);
    }

    @Then("^I click on (.*) Link$")
    public void IclickContacctPage(String Linktext) throws InterruptedException {
        CommonPageFunctions commonPageObj = new CommonPageFunctions(driver);
        commonPageObj.ClickOnLinkText(Linktext);
        Thread.sleep(10000);
    }

    @And("^I enter (.*) field with (.*)$")
    public void Ienterforenamefield(String fieldname, String TextValue) {
        CommonPageFunctions commonPageObj = new CommonPageFunctions(driver);
        commonPageObj.EnterTextIntoEditField(fieldname, TextValue);
    }

    @Then("^I Enter (.*) field with (.*) by id$")
    public void EnterValuesbyID(String fieldname, String TextValue) {
        CommonPageFunctions commonPageObj = new CommonPageFunctions(driver);
        commonPageObj.EnterTextIntoEditField_byID(fieldname.replace("\"",""), TextValue.replace("\"", ""));
    }

    @And("I verify the success message")
    public void iVerifyTheSuccessMessage() {
        try {
            String Success_message = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
            System.out.println("Succesmessage " + Success_message);
            if (Success_message.contains("Thanks"))
                System.out.println("Submission is successful");
            else
                System.out.println("Submission is not successful");
        }
        catch (NoSuchElementException e){
            System.err.println("Success message not displayed wait further");
        }
    }

    @And("^I click on (.*) buy button (.*) time$")
    public void iClickOnFunnyCowBuyButton(String productname, int itemCount) {
        CommonPageFunctions commonPageObj = new CommonPageFunctions(driver);
        List<WebElement> AllLinks = driver.findElements(By.linkText("Buy"));
        String ItemName;
        productname = productname.replace("\"", "");
        int Counter = AllLinks.size();
        for (int i = 1; i <= Counter; i++) {
            ItemName = commonPageObj.GetProductdetails(i);
            if (ItemName.contains(productname)) {
                System.out.println("Product "+ ItemName+" exists proceeding to click");
                for (int j=0 ; j< itemCount; j++) {
                    AllLinks.get(i-1).click();
                }
                Productnames[prodcounter] = productname;
                prodcounter++;
            }
        }
    }

    @Then("I verify items in the cart")
    public void iVerifyItemsInTheCart() {
        CommonPageFunctions commonPageObj = new CommonPageFunctions(driver);
        List<WebElement> RowList = commonPageObj.TableRows();
        int Rowcount = RowList.size();
        for (int i=1; i<=Rowcount; i++) {
            String itemName = commonPageObj.TableCellValue(i, 1);
            itemName = itemName.replace("\"", "");
            if (Productnames[i-1].contains(itemName)) {
                System.out.println();
                System.out.println("The product is present and the value is " + itemName);
                String AmountTest = commonPageObj.TableCellValue(i, 2);
                AmountTest = AmountTest.replace("$", "");
                double Amount =  Double.parseDouble(AmountTest);
                System.out.println(Amount);
                double itemcount = Double.parseDouble(commonPageObj.CellAttributeValue(i, 3));
                double subtotalC = itemcount * Amount;
                String Subtotal = commonPageObj.TableCellValue(i, 4);
                Subtotal = Subtotal.replace("$", "");
                double SubtotalAmount =  Double.parseDouble(Subtotal);
                if (SubtotalAmount == subtotalC) {
                    System.out.println("The Subtotal amount is matching, calculated amount is : "+subtotalC+ " and populated value is : "+SubtotalAmount);
                }
                else{
                    System.out.println("The Subtotal amount is not matching, The calculated amount is : "+subtotalC+ " and populated value is : "+SubtotalAmount);
                }

            }
        }

    }

    @After
    public void closebrowser(){
        driver.quit();
    }

}
