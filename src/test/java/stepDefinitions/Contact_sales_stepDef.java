package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import utilities.BrowserUtils;

import static stepDefinitions.Hooks.driver;

public class Contact_sales_stepDef extends CommonPage {

    @When("user clicks the button Request Demo")
    public void userClicksTheButtonRequestDemo() {
        contactSales().clickRequestDemoButton();
    }

    @Then("user verifies the URL of the new tab is {string}")
    public void userVerifiesTheURLOfTheNewTabIs(String url) {
        contactSales().verifyUrlOnTheNewTab(url);
    }

    @Then("user verifies the title Contact us now! appears")
    public void userVerifiesTheTitleContactUsNowAppears() {
        BrowserUtils.verifyElementDisplayed(contactSales().contactUsNowTitle);
    }

    @Then("user verifies the Contact us inboxes and checkboxes are functional")
    public void userVerifiesTheContactUsInboxesAndCheckboxesAreFunctional() {
        contactSales().verifyContactUsInboxesCheckboxes();
    }

    @And("user fills the First name inbox")
    public void userFillsTheFirstNameInbox() {
        contactSales().fillFirstName();
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
        contactSales().selectsACountry();
    }

    @And("user fills the How did you hear about us? inbox")
    public void userFillsTheHowDidYouHearAboutUsInbox() {
        contactSales().fillHowDidYouHear();
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
        contactSales().clickSendButton();
    }

    @Then("user verifies the Error Messages on the required fields")
    public void userVerifiesTheErrorMessagesOnTheRequiredFields(DataTable dataTable) {
        contactSales().verifyErrorMessage(dataTable);
    }

    @And("user clicks the button Send on the Contact Us page")
    public void userClicksTheButtonSendOnTheContactUsPage() {
        contactSales().clickSendButtonOnContactUs();
    }

    @And("user switches to the new tab")
    public void userSwitchesToTheNewTab() {
        BrowserUtils.switchToNewTab(driver);
    }

    @And("user switches to the default tab")
    public void userSwitchesToTheDefaultTab() {
        BrowserUtils.switchToDefaultTab(driver);
    }
}