package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import utilities.BrowserUtils;

public class MainPage_stepDef extends CommonPage {

    @Given("user lands on the Main Page")
    public void userLandsOnTheMainPage() {
        mainPage().landOnTheMainPage();
    }

    @Then("user verifies the URL of the page is {string}")
    public void userVerifiesTheUrlOfPage(String url) {
        mainPage().verifyUrl(url);
    }

    @Then("user verifies the company logo is visible")
    public void userVerifiesTheCompanyLogoIsVisible() {
        BrowserUtils.verifyElementDisplayed(mainPage().companyLogoOnHeaders);
    }

    @Then("user verifies the main banner is visible")
    public void userVerifiesTheMainBannerIsVisible() {
        BrowserUtils.verifyElementDisplayed(mainPage().mainBanner);
    }

    @Then("user verifies the navigation menu is visible and functional")
    public void userVerifiesTheNavigationMenuIsVisibleAndFunctional(DataTable dataTable) {
        mainPage().verifyNavigationMenu(dataTable);
    }

    @Then("user verifies the content titles are visible on Main page")
    public void userVerifiesTheContentTitlesAreVisibleOnMainPage(DataTable dataTable) {
        mainPage().verifyContentTitle(dataTable);
    }

    @When("user clicks the Header Item Links {string}")
    public void userClicksTheHeaderItemLinks(String link) {
        mainPage().clickHeaderItem(link);
    }

    @Then("user clicks the {string} in the navigation bar and verifies they lead to the correct {string}")
    public void userClicksTheInTheNavigationBarAndVerifiesTheyLeadToTheCorrect(String link, String url) {
        mainPage().clickLinksInNavigationBar(link, url);
    }

    @Then("user verifies the company logo is visible in the footer")
    public void userVerifiesTheCompanyLogoIsVisibleInTheFooter() {
        BrowserUtils.verifyElementDisplayed(mainPage().logoLocatorOnFooter);
    }

    @Then("user verifies the Copyright is visible in the footer")
    public void userVerifiesTheCopyrightIsVisibleInTheFooter() {
        BrowserUtils.verifyElementDisplayed(mainPage().copyrightLocator);
    }

    @Then("user verifies the Social Media Links are visible in the footer")
    public void userVerifiesTheSocialMediaLinksAreVisibleInTheFooter() {
        BrowserUtils.verifyElementDisplayed(mainPage().socialMediaLinksLocator);
    }

    @Then("user verifies the footer content links are present and functional")
    public void userVerifiesTheFooterContentLinksArePresentAndFunctional(DataTable dataTable) {
        mainPage().verifyFooterContentLinks(dataTable);
    }

    @When("user hovers over the link Company")
    public void userHoversOverTheLinkCompany() {
        mainPage().hoverOverLinkCompany();
    }

    @Then("user verifies the Company related links appear and functional")
    public void userVerifiesTheCompanyRelatedLinksAppearAndFunctional(DataTable dataTable) {
        mainPage().verifyCompanyRelatedLinks(dataTable);
    }

    @When("user clicks the Company Related Item Links {string}")
    public void user_clicks_the_company_related_item_links(String link) {
        mainPage().clickCompanyRelatedItemLinks(link);
    }

    @When("user hovers over the link Resources")
    public void userHoversOverTheLinkResources() {
        mainPage().resourcesLink.click();
    }

    @Then("user verifies the Resources related links appear and functional")
    public void userVerifiesTheResourcesRelatedLinksAppearAndFunctional(DataTable dataTable) {
        mainPage().verifyResourcesLinks(dataTable);
    }

    @When("user clicks the Resources Related Item Links {string}")
    public void user_clicks_the_resources_related_item_links(String link) {
        mainPage().clickResourcesRelatedItemLinks(link);
    }

    @When("user clicks the Footer Item Links {string}")
    public void userClicksTheFooterItemLinks(String link) {
        mainPage().clickFooterItemLinks(link);
    }

    @Then("user verifies the directed URL {string}")
    public void userVerifiesTheDirectedUrl(String url) {
        mainPage().verifyDirectedUrl(url);
    }

    @Then("user verifies the titles {string} of the page")
    public void userVerifiesTheTitlesOfThePage(String title) {
        mainPage().verifyTitleOfPage(title);
    }
}
