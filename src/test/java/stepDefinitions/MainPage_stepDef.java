package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.CommonPage;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.List;

import static stepDefinitions.Hooks.actions;
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

    @Then("user verifies the company logo is visible in the footer")
    public void userVerifiesTheCompanyLogoIsVisibleInTheFooter() {
        BrowserUtils.verifyElementDisplayed(mainPage().footerMenuList.get(0));
    }

    @Then("user verifies the footer content links are present and functional")
    public void userVerifiesTheFooterContentLinksArePresentAndFunctional(DataTable dataTable) {
        BrowserUtils.scrollToBottom();
        List<String> expectedLinks = dataTable.asList();

        // Verify that each expected link is present, visible, and clickable
        for (String expectedLinkTitle : expectedLinks) {
            // Find the element by text
            WebElement link = Driver.getDriver().findElement(By.xpath("//div[@class='flex_row  ']//div/ul/li"));

            // Assert visibility and clickability
            Assert.assertTrue("Element '" + expectedLinkTitle + "' is not visible", link.isDisplayed());
            Assert.assertTrue("Element '" + expectedLinkTitle + "' is not clickable", link.isEnabled());
        }
    }

    @When("user clicks the Footer Items link and verifies the expected URL")
    public void userClicksTheFooterItemsLinkAndVerifiesTheExpectedURL(DataTable dataTable) {

    }

    @When("user hovers over the link Company")
    public void userHoversOverTheLinkCompany() {
        BrowserUtils.waitForPageToLoad(25);
        BrowserUtils.waitForVisibility(mainPage().companyLink);
        actions.moveToElement(mainPage().companyLink).build().perform();
    }

    @Then("user verifies the Company related links appear and functional")
    public void userVerifiesTheCompanyRelatedLinksAppearAndFunctional(DataTable dataTable) {
        BrowserUtils.wait(1);
        List<String> expectedLinks = dataTable.asList();

        // Find the navigation menu elements
        List<WebElement> relatedLinksElements = mainPage().companyMenuLinks;

        // Verify that each expected links is present, visible, and clickable
        for (String expectedLinkTitles : expectedLinks) {
            boolean found = false;
            for (WebElement Links : relatedLinksElements) {
                String actualTitle = Links.getText();
                if (actualTitle.equals(expectedLinkTitles)) {
                    found = true;
                    Assert.assertTrue("Element '" + expectedLinkTitles + "' is not visible", Links.isDisplayed());
                    Assert.assertTrue("Element '" + expectedLinkTitles + "' is not clickable", Links.isEnabled());
                    break;
                }
            }
            if (!found) {
                Assert.fail("Element '" + expectedLinkTitles + "' is not found in the navigation menu");
            }
        }
    }

    @When("user hovers over the link Resources")
    public void userHoversOverTheLinkResources() {
        mainPage().resourcesLink.click();
    }

    @Then("user verifies the Resources related links appear and functional")
    public void userVerifiesTheResourcesRelatedLinksAppearAndFunctional(DataTable dataTable) {
        BrowserUtils.waitForPageToLoad(25);

        List<String> expectedLinks = dataTable.asList();

        // Verify that each expected link is present, visible, and clickable
        for (String expectedLinkTitle : expectedLinks) {
            // Wait for the visibility of the element by text
            BrowserUtils.waitForVisibility(expectedLinkTitle);

            // Find the element by text
            WebElement link = Driver.getDriver().findElement(By.xpath("//*[text()='" + expectedLinkTitle + "']"));

            // Assert visibility and clickability
            Assert.assertTrue("Element '" + expectedLinkTitle + "' is not visible", link.isDisplayed());
            Assert.assertTrue("Element '" + expectedLinkTitle + "' is not clickable", link.isEnabled());
        }
    }


}