package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.CommonPage;
import utilities.BrowserUtils;

import java.util.List;

import static stepDefinitions.Hooks.actions;
import static stepDefinitions.Hooks.driver;

public class Contact_sales_stepDef extends CommonPage {

    @When("user clicks the button Request Demo")
    public void userClicksTheButtonRequestDemo() {
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();

        driver.switchTo().frame(contactSales().iframeElement);
        contactSales().requestDemoButton.get(0).click();
    }

    @Then("user verifies the URL of the new tab is {string}")
    public void userVerifiesTheURLOfTheNewTabIs(String url) {
        BrowserUtils.switchToNewTab(driver);
        BrowserUtils.waitForPageToLoad(25);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("The current URL doesn't match the expected URL", url, currentUrl);
        System.out.println("The Current URL is: " + currentUrl);
    }

    @Then("user verifies the title Contact us now! appears")
    public void userVerifiesTheTitleContactUsNowAppears() {
        BrowserUtils.verifyElementDisplayed(contactSales().contactUsNowTitle);
    }

    @Then("user verifies the Contact us inboxes and checkboxes are functional")
    public void userVerifiesTheContactUsInboxesAndCheckboxesAreFunctional() {
        BrowserUtils.verifyElementClickable(contactSales().firstnameInbox);
        BrowserUtils.verifyElementClickable(contactSales().lastnameInbox);
        BrowserUtils.verifyElementClickable(contactSales().emailInbox);
        BrowserUtils.verifyElementClickable(contactSales().phoneNumberInbox);
        BrowserUtils.verifyElementClickable(contactSales().companyNameInbox);
        BrowserUtils.verifyElementClickable(contactSales().country_RegionInbox);
        BrowserUtils.verifyElementClickable(contactSales().location_of_headquartersInbox);
        BrowserUtils.verifyElementClickable(contactSales().agreementCheckbox);
        BrowserUtils.verifyElementClickable(contactSales().subscriptionCheckbox);
    }

    @And("user fills the First name inbox")
    public void userFillsTheFirstNameInbox() {
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

        if (iframes.size() >= 8) {
            driver.switchTo().frame(iframes.get(7));
        } else {
            System.out.println(iframes.size() + " iframes were found");
        }
        contactSales().firstnameInbox.sendKeys("Mehmet");
    }

    @And("user fills the Last name inbox")
    public void userFillsTheLastNameInbox() {
        contactSales().lastnameInbox.sendKeys("Sen");
    }

    @And("user fills the Email inbox")
    public void userFillsTheEmailInbox() {
        contactSales().emailInbox.sendKeys("hypnotes@gmail.com");
    }

    @And("user fills the Phone number inbox")
    public void userFillsThePhoneNumberInbox() {
        contactSales().phoneNumberInbox.sendKeys("+34747401919");
    }

    @And("user fills the Company name inbox")
    public void userFillsTheCompanyNameInbox() {
        contactSales().companyNameInbox.sendKeys("Hypnotes");
    }

    @And("user fills the Country_Region inbox")
    public void userFillsTheCountry_RegionInbox() {
        contactSales().country_RegionInbox.sendKeys("Spain");
    }

    @And("user selects a country from the Headquartered in inbox")
    public void userSelectsACountryFromTheHeadquarteredInInbox() {
        BrowserUtils.waitAndClick(contactSales().location_of_headquartersInbox, 2);
        Select select = new Select(contactSales().location_of_headquartersInbox);
        select.selectByValue("Spain");
//        actions.sendKeys(Keys.TAB).build().perform();
    }

    @And("user fills the How did you hear about us? inbox")
    public void userFillsTheHowDidYouHearAboutUsInbox() {
        contactSales().howDidYouHearAboutUsInbox.click();
        Select select = new Select(contactSales().howDidYouHearAboutUsInbox);
        select.selectByValue("Online advertising");
    }

    @And("user checks the Agreement checkbox")
    public void userChecksTheAgreementCheckbox() {
        BrowserUtils.waitAndClick(contactSales().agreementCheckbox, 2);
    }

    @And("user checks the subscription checkbox")
    public void userChecksTheSubscriptionCheckbox() {
        BrowserUtils.waitAndClick(contactSales().subscriptionCheckbox, 2);
    }

    @And("user clicks the button Send")
    public void userClicksTheButtonSend() {
        BrowserUtils.scrollToElement(driver, contactSales().sendButton);
        BrowserUtils.waitForClickability(contactSales().sendButton);
        contactSales().sendButton.click();
    }
}
