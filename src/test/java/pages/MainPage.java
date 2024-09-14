package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPage extends CommonPage {

    @FindBy(xpath = "//button[@id= 'hs-eu-confirmation-button']")
    public WebElement acceptAll;

    @FindBy(id = "hs_cos_wrapper_header_logo_hs_logo_widget")
    public WebElement companyLogoOnHeaders;

    @FindBy(css = ".header-banner-bg-image")
    public WebElement mainBanner;

    @FindBy(xpath = "(//ul[@class='active-branch'])[1]/li")
    public List<WebElement> navigationMenu;

    @FindBy(xpath = "")
    public WebElement aa;

    @FindBy(xpath = "")
    public WebElement aaa;








}
