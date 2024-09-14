package utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static stepDefinitions.Hooks.driver;

public class BrowserUtils {

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibility(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        By locator = By.xpath("//*[text()= '" + text + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void verifyElementDisplayed(WebElement element) {
        try {
            boolean isElementDisplayed = element.isDisplayed();
            System.out.println("Is Element Displayed: " + isElementDisplayed);

            Assert.assertTrue("Element is not visible: " + element, isElementDisplayed);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void verifyElementClickable(WebElement element) {
        try {
            Assert.assertTrue("Element is not clickable: " + element, element.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }

    public static void wait(int secs) {
        try {
            Thread.sleep(1000L * secs);
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException |
                 InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        BrowserUtils.waitForVisibility(element, 10);
    }

    public static void scrollToElement(JavascriptExecutor js, WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickAndVerify(WebDriver driver, By linkLocator, String expectedUrl) {
        driver.findElement(linkLocator).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        if (driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("Link verified: " + linkLocator + " leads to " + expectedUrl);
        } else {
            System.out.println("Error! Link " + linkLocator + " does not lead to " + expectedUrl);
        }
    }

    public static Map<String, String> readExpectedUrlsFromFile(String filename) throws Exception {
        Map<String, String> urls = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=");
            if (parts.length == 2) {
                urls.put(parts[0].trim(), parts[1].trim());
            } else {
                System.out.println("Invalid line in expected_urls.txt: " + line);
            }
        }
        reader.close();
        return urls;
    }
}

