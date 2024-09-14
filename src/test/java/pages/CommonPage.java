package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public abstract class CommonPage {

    public CommonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private MainPage mainPage;

    public MainPage mainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }
}
