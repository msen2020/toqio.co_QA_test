package stepDefinitions;

import io.cucumber.java.en.When;
import pages.CommonPage;

public class Contact_sales_stepDef extends CommonPage {

    @When("user clicks the button Request Demo")
    public void userClicksTheButtonRequestDemo() {
       contactSales().requestDemoButton.click();
    }
}
