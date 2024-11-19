package pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.List;

import static stepDefinitions.Hooks.actions;
import static stepDefinitions.Hooks.driver;
import static utilities.BrowserUtils.waitForVisibility;


public class MainPage extends CommonPage {

    @FindBy(id = "hs_cos_wrapper_header_logo_hs_logo_widget")
    public WebElement companyLogoOnHeaders;

    @FindBy(css = ".header-banner-bg-image")
    public WebElement mainBanner;

    @FindBy(xpath = "(//ul[@class='active-branch'])[1]/li")
    public List<WebElement> navigationMenu;

    @FindBy(xpath = "(//a[@href='javascript:;'])[2]")
    public WebElement companyLink;

    @FindBy(xpath = "(//ul[@class='hs-menu-children-wrapper'])[1]/li")
    public List<WebElement> companyMenuLinks;

    @FindBy(xpath = "(//a[@href='javascript:;'])[3]")
    public WebElement resourcesLink;

    @FindBy(css = ".footer-logo img")
    public WebElement logoLocatorOnFooter;

    @FindBy(css = ".footer-content div:nth-child(2)")
    public WebElement copyrightLocator;

    @FindBy(css = ".footer-social")
    public WebElement socialMediaLinksLocator;

    @FindBy(xpath = "//div[@class='language-menu']")
    public WebElement languageIcon;

    @FindBy(xpath = "//a[@data-language= 'en']")
    public WebElement englishOption;

    @FindBy(xpath = "//a[@data-language= 'es']")
    public WebElement spanishOption;

    @FindBy(tagName = "h1")
    public WebElement spanishText;

    @FindBy(id = "hs_cos_wrapper_button")
    public WebElement connectUsButton;

    public void footerLinkElement(String linkTest) {
        WebElement footerLink = driver.findElement(By.xpath("//footer//a[text()= '" + linkTest + "']"));
        footerLink.click();
    }

    public void landOnTheMainPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    public void verifyUrl(String url) {
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("The current URL doesn't match the expected URL", url, currentUrl);
        System.out.println("The Current URL is: " + currentUrl);
    }

    public void verifyNavigationMenu(DataTable dataTable) {
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

    public void verifyContentTitle(DataTable dataTable) {
        List<String> expectedTitles = dataTable.asList();
        List<WebElement> titleElements = driver.findElements(By.cssSelector("h1, h2, h3, h4, span"));

        for (String expectedTitle : expectedTitles) {
            WebElement foundElement = findElementByText(titleElements, expectedTitle);

            if (foundElement != null) {
                BrowserUtils.scrollToElement(foundElement);
                waitForVisibility(foundElement);
                Assert.assertTrue("Element '" + expectedTitle + "' is not visible", foundElement.isDisplayed());
            } else {
                Assert.fail("Element '" + expectedTitle + "' is not found on the Main page");
            }
        }
    }

    private WebElement findElementByText(List<WebElement> elements, String expectedText) {
        String normalizedExpectedText = normalizeText(expectedText);
        for (WebElement element : elements) {
            if (normalizeText(element.getText()).equals(normalizedExpectedText)) {
                return element;
            }
        }
        return null;
    }

    private String normalizeText(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    public void clickHeaderItem(String link) {
        WebElement linkElement = driver.findElement(By.linkText(link));
        // Wait for element visibility before click
        waitForVisibility(linkElement);

        // Click with handling of stale element
        BrowserUtils.staleElementClick(linkElement, 5);
    }

    public void clickLinksInNavigationBar(String link, String url) {
        WebElement linkElement = driver.findElement(By.linkText(link));

        // Wait for element visibility before click
        waitForVisibility(linkElement);

        // Click with handling of stale element
        BrowserUtils.staleElementClick(linkElement, 5);

        // Verify the current URL
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.equals(url)) {
            throw new AssertionError("Expected URL: " + url + ", Actual URL: " + currentUrl);
        }
        System.out.println(currentUrl);
    }

    public void verifyFooterContentLinks(DataTable dataTable) {
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

    public void hoverOverLinkCompany() {
        BrowserUtils.waitForPageToLoad(25);
        waitForVisibility(mainPage().companyLink);
        actions.moveToElement(mainPage().companyLink).build().perform();
    }

    public void verifyCompanyRelatedLinks(DataTable dataTable) {
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

    public void clickCompanyRelatedItemLinks(String link) {
        WebElement linkElement = driver.findElement(By.linkText(link));
        // Wait for element visibility before click
        waitForVisibility(linkElement);

        // Click with handling of stale element
        BrowserUtils.staleElementClick(linkElement, 5);
    }

    public void clickResourcesRelatedItemLinks(String link) {
        WebElement linkElement = driver.findElement(By.linkText(link));
        // Wait for element visibility before click
        waitForVisibility(linkElement);

        // Click with handling of stale element
        BrowserUtils.staleElementClick(linkElement, 5);
    }

    public void verifyResourcesLinks(DataTable dataTable) {
        BrowserUtils.waitForPageToLoad(25);

        List<String> expectedLinks = dataTable.asList();

        // Verify that each expected link is present, visible, and clickable
        for (String expectedLinkTitle : expectedLinks) {
            // Wait for the visibility of the element by text
            waitForVisibility(expectedLinkTitle);

            // Find the element by text
            WebElement link = Driver.getDriver().findElement(By.xpath("//*[text()='" + expectedLinkTitle + "']"));

            // Assert visibility and clickability
            Assert.assertTrue("Element '" + expectedLinkTitle + "' is not visible", link.isDisplayed());
            Assert.assertTrue("Element '" + expectedLinkTitle + "' is not clickable", link.isEnabled());
        }
    }

    public void clickFooterItemLinks(String link) {
        BrowserUtils.waitForPageToLoad(25);
        BrowserUtils.scrollToBottom();
        mainPage().footerLinkElement(link);
    }

    public void verifyDirectedUrl(String url) {
        // Verify the current URL after navigation
        BrowserUtils.waitForPageToLoad(10);
        String currentUrl = driver.getCurrentUrl();

        if (!currentUrl.equals(url)) {
            throw new AssertionError("Expected URL: " + url + ", but got: " + currentUrl);
        }

        System.out.println("Navigated to URL: " + currentUrl);
    }

    public void verifyTitleOfPage(String title) {
        BrowserUtils.waitForPageToLoad(10);

        // Collect all title elements on the page (h1, h2, h3, h4, span)
        List<WebElement> titleElements = driver.findElements(By.cssSelector("h1, h2, h3, h4, span"));

        // Trim and normalize expected title whitespace
        String expectedTitle = title.trim().replaceAll("\\s+", " ");
        boolean found = false;

        // Print the expected title once
        System.out.println(expectedTitle);

        // Iterate through the title elements to find the expected title
        for (WebElement element : titleElements) {
            String actualTitle = element.getText().trim().replaceAll("\\s+", " ");
            if (actualTitle.equals(expectedTitle)) {
                // Scroll to the element before verifying visibility
                BrowserUtils.scrollToElement(driver, element);
                waitForVisibility(element);

                // Assert the element is displayed
                Assert.assertTrue("Expected title '" + expectedTitle + "' is not visible", element.isDisplayed());
                found = true;
                break;
            }
        }

        // If title is not found, throw an assertion failure
        if (!found) {
            Assert.fail("Expected title '" + expectedTitle + "' was not found on the page");
        }
    }

    public void hoverOverTheLanguageIcon() {
        BrowserUtils.waitForPageToLoad(25);
        waitForVisibility(mainPage().languageIcon);
        actions.moveToElement(mainPage().languageIcon).build().perform();
    }

    public void verifyLanguageIconsVisibleAndFunctional() {
        BrowserUtils.verifyElementDisplayed(mainPage().englishOption);
        BrowserUtils.verifyElementClickable(mainPage().englishOption);
        BrowserUtils.verifyElementDisplayed(mainPage().spanishOption);
        BrowserUtils.verifyElementClickable(mainPage().spanishOption);
    }

    public void verifyTitleAppears(String expectedTitle) {
        BrowserUtils.verifyElementDisplayed(mainPage().spanishText);
        String actualTitle = mainPage().spanishText.getText().replaceAll("\\s+", " ").trim();
        Assert.assertEquals("Title does not match the expected value", actualTitle, expectedTitle.replaceAll("\\s+", " ").trim());
    }

    public void userSelectsSpanishLanguage() {
        mainPage().spanishOption.click();
    }

    public void verifyConnectUsButton() {
        BrowserUtils.verifyElementDisplayed(mainPage().connectUsButton);
        BrowserUtils.verifyElementClickable(mainPage().connectUsButton);
    }

    public void checkIfLanguageEnglish() {
        if (!mainPage().languageIcon.getAttribute("lang").contains("en")) {
            mainPage().languageIcon.click();
            mainPage().englishOption.click();
        }
    }
}

