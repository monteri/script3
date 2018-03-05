package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    /**
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        if ("firefox".equals(browser)) {
            System.setProperty(
                    "webdriver.gecko.driver", new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
            return new FirefoxDriver();
        } else if ("internet explorer".equals(browser)) {
            System.setProperty(
                    "webdriver.ie.driver", new File(BaseScript.class.getResource("/IEDriverServer.exe").getFile()).getPath());
            return new InternetExplorerDriver();
        } else if ("chrome".equals(browser)) {
            System.setProperty(
                    "webdriver.chrome.driver", new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
            return new ChromeDriver();
        } else {
            System.setProperty(
                    "webdriver.chrome.driver",
                    new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
            return new ChromeDriver();
        }
    }

    public static WebDriver getConfiguredDriver() {
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void login(WebDriver driver) {
        driver.get(Properties.getBaseAdminUrl());

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("webinar.test@gmail.com");

        WebElement pass = driver.findElement(By.id("passwd"));
        pass.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement button = driver.findElement(By.name("submitLogin"));
        button.click();
    }

    public static void createCategory(WebDriver driver) {
        Actions actions = new Actions(driver);

        WebElement catalog = driver.findElement(By.cssSelector("#subtab-AdminCatalog > a"));
        actions.moveToElement(catalog).build().perform();

        WebElement categories = driver.findElement(By.id("subtab-AdminCategories"));
        categories.click();

        WebElement create = driver.findElement(By.id("page-header-desc-category-new_category"));
        create.click();

        WebElement field1 = driver.findElement(By.id("name_1"));
        field1.sendKeys("Одежда");

        WebElement save = driver.findElement(By.id("category_form_submit_btn"));
        save.click();

        WebElement sortByName = driver.findElement(By.xpath("//*[@id=\"table-category\"]/thead/tr[1]/th[3]/span/a[1]/i"));
        sortByName.click();
    }

    public static void connectLogger(WebDriver driver) {
        EventFiringWebDriver event = new EventFiringWebDriver(driver);
        event.register(new WebDriverLogger());
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link ChromeDriver} instance by default.
     */
    /*public static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = getDriver();

       // TODO configure browser window (set timeouts, browser pindow position) and connect loggers.
        throw new UnsupportedOperationException("Method doesn't return configured WebDriver instance");
    }*/
}