package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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
    public List<WebElement> errorMessages;
}
