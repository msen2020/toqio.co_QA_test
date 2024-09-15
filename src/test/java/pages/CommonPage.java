package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public abstract class CommonPage {

    public CommonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private MainPage mainPage;
    private Contact_salesPage contactSales ;

    public MainPage mainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public Contact_salesPage contactSales() {
        if (contactSales == null) {
            contactSales = new Contact_salesPage();
        }
        return contactSales;
    }
}
