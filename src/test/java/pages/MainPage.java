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

    @FindBy(xpath = "(//a[@href='javascript:;'])[2]")
    public WebElement companyLink;

    @FindBy(xpath = "(//ul[@class='hs-menu-children-wrapper'])[1]/li")
    public List<WebElement> companyMenuLinks;

    @FindBy(xpath = "(//a[@href='javascript:;'])[3]")
    public WebElement resourcesLink;

    @FindBy(css = ".hs-menu-item.hs-menu-depth-1.active.active-branch:nth-child(1)")
    public WebElement homeLinkFooter;

    @FindBy(xpath = "//div[@class='flex_row  ']//div/ul/li")
    public List<WebElement> footerMenuList;

    @FindBy(css = ".footer-logo img")
    public WebElement logoLocator;

    @FindBy(css = ".footer-content div:nth-child(2) div")
    public WebElement copyrightLocator;

    @FindBy(css = ".footer-social-item")
    public WebElement socialMediaLinksLocator;

    @FindBy(xpath = "")
    public WebElement aaa;








}
