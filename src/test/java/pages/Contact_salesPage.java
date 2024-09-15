package pages;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Contact_salesPage extends CommonPage {

    @FindBy(xpath = "//a[@href='https://toqio.co/contact-sales/']")
    public WebElement requestDemoButton;

    @FindBy(xpath = "")
    public WebElement aa;
}
