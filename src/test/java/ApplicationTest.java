import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.time.Duration.ofSeconds;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ApplicationTest
{
    private WebDriver driver;

    @Before
    public void setUp()
    {
        chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        driver.get("https://www.saucedemo.com/");
        var wait = new WebDriverWait(driver, ofSeconds(10));
        var element = wait.
                until(presenceOfElementLocated(By.id("user-name")));
        assertTrue(element.isDisplayed());
    }

    @Test
    public void locatorExam()
    {
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        driver.get("https://www.saucedemo.com/");

        //Use CSS Selectors
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".btn_action")).click();

        //Use CSS/Xpath
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")).click();
        driver.findElement(By.cssSelector("#shopping_cart_container")).click();
        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

        //Use the best locator
        driver.findElement(By.id("first-name")).sendKeys("Costin");
        driver.findElement(By.id("last-name")).sendKeys("Suhan");
        driver.findElement(By.id("postal-code")).sendKeys("zip");
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();
        driver.findElement(By.cssSelector("#finish")).click();

        assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());
    }



//    @Test
//    public void typeOfLocators()
//    {
//        var driver = new ChromeDriver();
//        driver.get("https://www.saucedemo.com/");
//        var elementId = driver.findElement(By.id("user-name"));
//        var elementName = driver.findElement(By.name("name"));
//        var elementClass = driver.findElement(By.className("form_input"));
//        var elementTag = driver.findElement(By.tagName("input"));
//        var elementCssSelector = driver.findElement(By.cssSelector("#user-name"));
//        var elementXpath = driver.findElement(By.xpath(" //*[@id=\"user-name\"]"));
//        var elementLink = driver.findElement(By.linkText("Click me using this link text!"));
//        var elementPartialLink = driver.findElement(By.partialLinkText("this link text!"));
//    }
}