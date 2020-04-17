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
        Table table = new Table(By.xpath("//table[@class='table-data-sheet']"), driver);

        Assert.assertEquals("Viscose",
                table.getValueFromCellFromTableWithoutHeadings(1,2));
        Assert.assertEquals("Dressy",
                table.getValueFromCellFromTableWithoutHeadings(2,2));
        Assert.assertEquals("Short Dress",
                table.getValueFromCellFromTableWithoutHeadings(3,2));
    }

    @Test
    public void checkScenarioTwo() {
        mainPage.clickOnDressesThroughHover("Evening");
        mainPage.getPrintedDressInformation();
        mainPage.selectDressSize("M");
        mainPage.selectDressColor("Pink");
        mainPage.clickAddToCartButtonAndContinueShopping();

        Assert.assertEquals("1", mainPage.getCartText());
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

        Assert.assertEquals("Your shopping cart is empty.", mainPage.getNotificationText());
        Assert.assertEquals("(empty)", mainPage.getCartMessage());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
