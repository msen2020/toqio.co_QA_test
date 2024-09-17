package utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

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

    public static void waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        wait.until(ExpectedConditions.visibilityOf(element));
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
        } catch (TimeoutException e) {
            // Handle timeout exception gracefully
            String errorMessage = "Page load timed out after " + timeOutInSeconds + " seconds.";
            throw new RuntimeException(errorMessage, e);
        } catch (Throwable e) {
            // Handle other exceptions
            throw new RuntimeException("Error waiting for page load: " + e.getMessage(), e);
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

    public static void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        wait(2);
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToElementAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void staleElementClick(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void switchToNewTab(WebDriver driver) {
        // Get the current window handle (the parent window)
        String parentWindowHandle = driver.getWindowHandle();

        // Get all available window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Iterate through each handle and switch to the new tab/window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break; // Switched to the new tab, so exit the loop
            }
        }
    }

    public static void staleElementSolutionForVisibility(WebElement element) {
        Duration timeout = Duration.ofSeconds(30);
        new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    try {
                        return element.isDisplayed(); // Elementin görünür olup olmadığını kontrol et
                    } catch (StaleElementReferenceException e) {
                        return false; // Stale element hatası alınırsa false döner ve yeniden dener
                    }
                });
    }
}

