package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.CommonPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

import static stepDefinitions.Hooks.driver;

public class MainPage_stepDef extends CommonPage {

    @Given("user lands on the Main Page")
    public void userLandsOnTheMainPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @Then("user verifies the URL of the page is {string}")
    public void userVerifiesTheUrlOfPage(String url) {
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("The current URL doesn't match the expected URL", url, currentUrl);
        System.out.println("The Current URL is: " + currentUrl);
    }

    @Then("user verifies the company logo is visible")
    public void userVerifiesTheCompanyLogoIsVisible() {
        BrowserUtils.verifyElementDisplayed(mainPage().companyLogoOnHeaders);
    }

    @Then("user verifies the main banner is visible")
    public void userVerifiesTheMainBannerIsVisible() {
        BrowserUtils.verifyElementDisplayed(mainPage().mainBanner);
    }

    @Then("user verifies the navigation menu is visible and functional")
    public void userVerifiesTheNavigationMenuIsVisibleAndFunctional(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();

        // Find the navigation menu elements
        List<WebElement> navigationMenuElements = mainPage().navigationMenu;

        // Verify that each expected title is present, visible, and clickable
        for (String expectedTitle : expectedTitles) {
            boolean found = false;
            for (WebElement element : navigationMenuElements) {
                String actualTitle = element.getText();
                if (actualTitle.equals(expectedTitle)) {
                    found = true;
                    Assert.assertTrue("Element '" + expectedTitle + "' is not visible", element.isDisplayed());
                    Assert.assertTrue("Element '" + expectedTitle + "' is not clickable", element.isEnabled());
                    break;
                }
            }
            if (!found) {
                Assert.fail("Element '" + expectedTitle + "' is not found in the navigation menu");
            }
        }
    }

    @Then("user verifies the content titles are visible on Main page")
    public void userVerifiesTheContentTitlesAreVisibleOnMainPage(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        List<WebElement> titleElements = driver.findElements(By.cssSelector("h1, h2, h3, h4, span"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Verify each expected title
        for (String expectedTitle : expectedTitles) {
            boolean found = false;
            for (WebElement element : titleElements) {
                String actualTitle = element.getText().trim().replaceAll("\\s+", " "); // Trim and normalize whitespace
                if (actualTitle.equals(expectedTitle.trim().replaceAll("\\s+", " "))) {
                    // Scroll to the element before verifying visibility
                    BrowserUtils.scrollToElement(js, element);
                    BrowserUtils.waitForVisibility(element);

                    Assert.assertTrue("Element '" + expectedTitle + "' is not visible", element.isDisplayed());
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail("Element '" + expectedTitle + "' is not found on the Main page");
            }
        }
    }

    @Then("user clicks the links in the navigation bar and verifies they lead to the correct pages")
    public void userClicksNavigationLinks(DataTable dataTable) {
        // Convert the DataTable to a List of Maps (for key-value pairs)
        List<Map<String, String>> navigationLinks = dataTable.asMaps(String.class, String.class);

        // Loop through the navigation menu items
        for (int i = 2; i < mainPage().navigationMenu.size(); i++) {
            WebElement menuLink = mainPage().navigationMenu.get(i);

            // Wait for the visibility of the element
            BrowserUtils.waitForVisibility(menuLink);

            // Click on the menu link
            menuLink.click();

            // Add synchronization time (e.g., wait for page load)
            BrowserUtils.waitForPageToLoad(30);

            // Get the current URL and the expected URL from the DataTable
            String expectedUrl = navigationLinks.get(i - 1).get("URL");
            String currentUrl = Driver.getDriver().getCurrentUrl();

            // Verify the URL matches the expected URL
            Assert.assertEquals("The URL did not match for menu item: " + (i + 1), expectedUrl, currentUrl);

            // Navigate back to the main page
            Driver.getDriver().navigate().back();

            // Wait for the navigation menu to be visible again after going back
            BrowserUtils.waitForVisibility(mainPage().navigationMenu.get(1));
        }
    }

//    @When("user clicks the Footer Items link and verifies the expected URL")
//    public void userClicksTheFooterItemsLinkAndVerifiesTheExpectedURL(DataTable dataTable) {
//
//    }

    @Then("user verifies the footer content links are present and functional")
    public void userVerifiesTheFooterContentLinksArePresentAndFunctional(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();

        // Find the navigation menu elements
        List<WebElement> navigationMenuElements = mainPage().navigationMenu;

        // Verify that each expected title is present, visible, and clickable
        for (String expectedTitle : expectedTitles) {
            boolean found = false;
            for (WebElement element : navigationMenuElements) {
                String actualTitle = element.getText();
                if (actualTitle.equals(expectedTitle)) {
                    found = true;
                    Assert.assertTrue("Element '" + expectedTitle + "' is not visible", element.isDisplayed());
                    Assert.assertTrue("Element '" + expectedTitle + "' is not clickable", element.isEnabled());
                    break;
                }
            }
            if (!found) {
                Assert.fail("Element '" + expectedTitle + "' is not found in the navigation menu");
            }
        }
    }

    @Then("user clicks the {string} in the navigation bar and verifies they lead to the correct {string}")
    public void userClicksTheInTheNavigationBarAndVerifiesTheyLeadToTheCorrect(String link, String url) {
        WebElement linkElement = driver.findElement(By.linkText(link));

        // Wait for element visibility before click
        BrowserUtils.waitForVisibility(linkElement);

        // Click with handling of stale element
        BrowserUtils.staleElementClick(linkElement, 5);

        // Verify the current URL
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(url)) {
            throw new AssertionError("Expected URL: " + url + ", Actual URL: " + currentUrl);
        }
        System.out.println(currentUrl);
    }
}