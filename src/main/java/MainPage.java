import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage {

    private WebDriver driver;
    public MainPage(WebDriver driver) { this.driver = driver; }

    private By dressesButton = By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a[text()='Dresses']");
    private By printedDress = By.xpath("//div[@class='product-image-container']/a[1]");
    private By printedDressMoreButton = By.xpath("//div[@id=\"center_column\"]//span[text()='More']");
    private By addToCartButton = By.xpath("//*[@id=\"add_to_cart\"]/button/span");
    private By continueShoppingButton = By.xpath("//*[@id=\"layer_cart\"]//span[@title=\"Continue shopping\"]/span");
    private By cartButton = By.xpath("//a[@title=\"View my shopping cart\"]");
    private By checkoutButton = By.xpath("//a[@id=\"button_order_cart\"]/span");

    public MainPage getPrintedDressInformation() {
        WebElement dress = driver.findElement(printedDress);
        WebElement moreButton = driver.findElement(printedDressMoreButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(dress).build().perform();
        if (moreButton.isDisplayed())
            actions.moveToElement(moreButton).build().perform();
        moreButton.click();
        return new MainPage(driver);
    }

    public MainPage clickOnDressesThroughHover(String type) {
        String dressType = String.format("//div[@id=\"block_top_menu\"]/ul/li[2]/ul//li//a[@title=\"%s Dresses\"]", type);
        WebElement dresses = driver.findElement(dressesButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(dresses).build().perform();
        driver.findElement(By.xpath(dressType)).click();
        return new MainPage(driver);
    }

    public MainPage openCheckout() {
        WebElement cart = driver.findElement(cartButton);
        WebElement checkout = driver.findElement(checkoutButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(cart).build().perform();
        checkout.click();
        return new MainPage(driver);
    }

    public MainPage deleteItemFromCheckout(String number) {
        String itemNumberToDeleteInCheckout = String.format("//table[@id=\"cart_summary\"]/tbody/tr[%s]/td[@class=\"cart_delete text-center\"]//a", number);
        driver.findElement(By.xpath(itemNumberToDeleteInCheckout)).click();
        return new MainPage(driver);
    }

    public MainPage selectDressSize(String size) {

        driver.findElement(By.xpath("//*[@id=\"group_1\"]")).click();

        if (size.equalsIgnoreCase("S")) {
            driver.findElement(By.xpath("//*[@id=\"group_1\"]/option[1]")).click();
        } else if (size.equalsIgnoreCase("M")) {
            driver.findElement(By.xpath("//*[@id=\"group_1\"]/option[2]")).click();
        } else if (size.equalsIgnoreCase("L")) {
            driver.findElement(By.xpath("//*[@id=\"group_1\"]/option[3]")).click();
        }

        return new MainPage(driver);
    }

    public MainPage selectDressColor(String color) {

        if (color.equalsIgnoreCase("Beige")) {
            driver.findElement(By.xpath("//a[@name=\"Beige\"]")).click();
        } else if (color.equalsIgnoreCase("Pink")) {
            driver.findElement(By.xpath("//a[@name=\"Pink\"]")).click();
        }

        return new MainPage(driver);
    }

    public MainPage clickAddToCartButtonAndContinueShopping() {
        driver.findElement(addToCartButton).click();
        driver.findElement(continueShoppingButton).click();
        return new MainPage(driver);
    }

}