package pages;

import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.BrowserUtils;

import java.util.Arrays;
import java.util.List;

import static stepDefinitions.Hooks.actions;
import static stepDefinitions.Hooks.driver;

public class Contact_salesPage extends CommonPage {

    @FindBy(xpath = "//a[@class='interactive-button']")
    public List<WebElement> requestDemoButton;

    @FindBy(css = "iframe[src*='hs-web-interactive']")
    public WebElement iframeElement;

    @FindBy(tagName = "h1")
    public WebElement contactUsNowTitle;

    @FindBy(name = "firstname")
    public WebElement firstnameInbox;

    @FindBy(name = "lastname")
    public WebElement lastnameInbox;

    @FindBy(name = "email")
    public WebElement emailInbox;

    @FindBy(name = "phone")
    public WebElement phoneNumberInbox;

    @FindBy(name = "company")
    public WebElement companyNameInbox;

    @FindBy(name = "0-2/country")
    public WebElement country_RegionInbox;

    @FindBy(name = "location_of_headquarters")
    public WebElement location_of_headquartersInbox;

    @FindBy(name = "how_did_you_hear_about_us_")
    public WebElement howDidYouHearAboutUsInbox;

    @FindBy(xpath = "(//li[@class='hs-form-booleancheckbox'])[1]")
    public WebElement agreementCheckbox;

    @FindBy(xpath = "(//li[@class='hs-form-booleancheckbox'])[2]")
    public WebElement subscriptionCheckbox;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement sendButton;

    @FindBy(xpath = "//label[@class='hs-error-msg hs-main-font-element']")
    public WebElement errorMessage1;

    @FindBy(xpath = "//label[@class='hs-main-font-element']")
    public WebElement errorMessage2;

    public void clickRequestDemoButton() {
        BrowserUtils.waitForPageToLoad(25);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        driver.switchTo().frame(contactSales().iframeElement);
        BrowserUtils.waitForClickability(contactSales().requestDemoButton.get(0));
        contactSales().requestDemoButton.get(0).click();
    }

    public void verifyUrlOnTheNewTab(String url) {
        BrowserUtils.wait(1);
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("The current URL doesn't match the expected URL", url, currentUrl);
        System.out.println("The Current URL is: " + currentUrl);
    }

    public void verifyContactUsInboxesCheckboxes() {
        List<WebElement> elementsToCheck = Arrays.asList(
                contactSales().firstnameInbox,
                contactSales().lastnameInbox,
                contactSales().emailInbox,
                contactSales().phoneNumberInbox,
                contactSales().companyNameInbox,
                contactSales().country_RegionInbox,
                contactSales().location_of_headquartersInbox,
                contactSales().agreementCheckbox,
                contactSales().subscriptionCheckbox
        );

        for (WebElement element : elementsToCheck) {
            verifyElementFunctional(element);
        }
    }

    private void verifyElementFunctional(WebElement element) {
        Assert.assertTrue("Element is not displayed", element.isDisplayed());
        Assert.assertTrue("Element is not enabled", element.isEnabled());
    }

    public void fillFirstName() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        if (iframes.size() >= 8) {
            driver.switchTo().frame(iframes.get(7));
        } else {
            System.out.println(iframes.size() + " iframes were found");
        }
        contactSales().firstnameInbox.sendKeys("Mehmet");
    }

    public void selectsACountry() {
        BrowserUtils.waitAndClick(contactSales().location_of_headquartersInbox, 2);
        Select select = new Select(contactSales().location_of_headquartersInbox);
        select.selectByValue("Spain");
    }

    public void fillHowDidYouHear() {
        contactSales().howDidYouHearAboutUsInbox.click();
        Select select = new Select(contactSales().howDidYouHearAboutUsInbox);
        select.selectByValue("Online advertising");
    }

    public void clickSendButton() {
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        BrowserUtils.scrollToElement(driver, contactSales().sendButton);
        BrowserUtils.waitForClickability(contactSales().sendButton);
        contactSales().sendButton.click();
    }

    public void verifyErrorMessage(DataTable dataTable) {
        List<String> expectedMessages = dataTable.asList();
        List<WebElement> errorElements = Arrays.asList(contactSales().errorMessage1, contactSales().errorMessage2);

        for (String expectedMessage : expectedMessages) {
            boolean messageFound = false;
            for (WebElement errorElement : errorElements) {
                if (errorElement.isDisplayed() && errorElement.getText().equals(expectedMessage)) {
                    messageFound = true;
                    break;
                }
            }
            Assert.assertTrue("Error message not found: " + expectedMessage, messageFound);
        }

        System.out.println("All expected error messages are displayed and verified: " + expectedMessages);
    }

    public void clickSendButtonOnContactUs() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        if (iframes.size() >= 8) {
            driver.switchTo().frame(iframes.get(7));
        } else {
            System.out.println(iframes.size() + " iframes were found");
        }
        BrowserUtils.scrollToBottom();
        BrowserUtils.scrollToElement(driver, contactSales().sendButton);
        BrowserUtils.waitForClickability(contactSales().sendButton);
        BrowserUtils.verifyElementClickable(contactSales().sendButton);
        contactSales().sendButton.click();
    }
}
