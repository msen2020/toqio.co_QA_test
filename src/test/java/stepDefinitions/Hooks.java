package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import pages.CommonPage;
import utilities.BrowserUtils;
import utilities.Driver;

public class Hooks extends CommonPage {

    public static boolean isHeadless = false;
    public static String browserType = "chrome";

    public static WebDriver driver; // Declare WebDriver instance in Hooks class
    public static String baseUrl;
    public static Actions actions;
    public static Random random = new Random();

    @Before(order = 1)
    public void setUp() {
        // This will run before each test method
        driver = Driver.getDriver();
        actions = new Actions(driver);
        baseUrl = "https://toqio.co/";
    }

    @Before(order = 2) // You can adjust the order if needed (e.g., for other Before methods)
    public void navigateToBaseUrl() {
        // Add your logic to navigate to the base URL here
        driver.get(baseUrl);
        acceptCookies(driver);
        BrowserUtils.storeParentWindowHandle(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies(); //Clear cookies after each test
//            Driver.closeDriver();
        }
    }

    public static void acceptCookies(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check if the cookie banner is already accepted
        if (isCookieBannerAccepted(driver)) {
            return;
        }

        // Find and click the accept cookies button
        WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id= 'hs-eu-confirmation-button']")));
        acceptButton.click();

        // Set a cookie to indicate cookies are accepted
        driver.manage().addCookie(new Cookie("cookiesAccepted", "true"));
    }

    private static boolean isCookieBannerAccepted(WebDriver driver) {
        Cookie cookie = driver.manage().getCookieNamed("cookiesAccepted");
        return cookie != null && cookie.getValue().equals("true");
    }
}