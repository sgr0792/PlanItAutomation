package Pages;

//import jdk.internal.misc.OSEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CommonPageFunctions {
    public WebDriver driver;

    public CommonPageFunctions(WebDriver driver){
        this.driver = driver;
    }
//Launch the Browser and open Jupiter website
    public void LaunchBrowser() {
        String driverPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", driverPath + "/src/test/resources/Driver/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("http://jupiter.cloud.planittesting.com");
        System.out.println(driver.getTitle());
        System.out.println("Web browser Launched");
    }

//    Click on any link using text of the link using contains check
    public void ClickOnLinkText(String LinkText) {
        if (driver.findElement(By.xpath("//a[contains(.,"+LinkText+")]")).isDisplayed()) {
            System.out.println("The link is found and the value is : " + LinkText);
            driver.findElement(By.xpath("//a[contains(.,"+LinkText+")]")).click();
        }
    }

//    Enter the value in any text field/input tag
    public void EnterTextIntoEditField(String FieldName, String TextValue) {
        driver.findElement(By.xpath("//form[@name='form']//div[@class='controls']/input[@id = "+FieldName+"]")).sendKeys(TextValue);
    }

//    Enter the value into any text field using ID as attribute value
    public void EnterTextIntoEditField_byID(String FieldName, String TextValue) {
        driver.findElement(By.id(FieldName)).sendKeys(TextValue);
    }

//    Return a product's name
    public String GetProductdetails (int i){
        return driver.findElement(By.xpath("(//li[@class = \"product ng-scope\"]/div/h4[@class=\"product-title ng-binding\"])[" + i + "]")).getText();
    }

//    Return a list of rows
    public List TableRows() {
        return driver.findElements(By.xpath("//form[@name=\"form\"]/table/tbody/tr"));
    }

//    Return a cell value of a particular row
    public String TableCellValue(int Rowvalue, int Cell) {
        return driver.findElement(By.xpath("//form[@name=\"form\"]/table/tbody/tr["+Rowvalue+"]/td["+Cell+"]")).getText();
    }

//    Return a attribute value of a particular cell
    public String CellAttributeValue(int Rowvalue, int Cell) {
        return driver.findElement(By.xpath("//form[@name=\"form\"]/table/tbody/tr["+Rowvalue+"]/td["+3+"]/input")).getAttribute("value");
    }

}