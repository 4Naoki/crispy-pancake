import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/nikita/IdeaProjects/testselenium/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        mainPage = new MainPage(driver);
    }

    @Test
    public void checkScenarioOne() {
        mainPage.clickOnDressesThroughHover("Evening");
        mainPage.getPrintedDressInformation();
        WebElement tableElement = driver.findElement(By.xpath("//table[@class='table-data-sheet']"));
        Table table = new Table(tableElement, driver);

        String Compositions = table.getValueFromCellFromTableWithoutHeadings(1,2);
        String Styles = table.getValueFromCellFromTableWithoutHeadings(2,2);
        String Properties = table.getValueFromCellFromTableWithoutHeadings(3,2);

        Assert.assertEquals("Viscose", Compositions);
        Assert.assertEquals("Dressy", Styles);
        Assert.assertEquals("Short Dress", Properties);
    }

    @Test
    public void checkScenarioTwo() {
        mainPage.clickOnDressesThroughHover("Evening");
        mainPage.getPrintedDressInformation();
        mainPage.selectDressSize("M");
        mainPage.selectDressColor("Pink");
        mainPage.clickAddToCartButtonAndContinueShopping();

        String Quantity = driver.findElement(By.xpath("//span[@class=\"ajax_cart_quantity unvisible\"]")).getText();

        Assert.assertEquals("1", Quantity);
    }

    @Test
    public void checkScenarioThree() {
        mainPage.clickOnDressesThroughHover("Evening");
        mainPage.getPrintedDressInformation();
        mainPage.selectDressSize("M");
        mainPage.selectDressColor("Pink");
        mainPage.clickAddToCartButtonAndContinueShopping();
        mainPage.openCheckout();
        mainPage.deleteItemFromCheckout("1");

        String Notification = driver.findElement(By.xpath("//div[@id=\"center_column\"]/p")).getAttribute("innerText");
        String cartMessage = driver.findElement(By.xpath("//a[@title=\"View my shopping cart\"]//span[text()=\"(empty)\"]")).getAttribute("innerText");

        Assert.assertEquals("Your shopping cart is empty.", Notification);
        Assert.assertEquals("(empty)", cartMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
