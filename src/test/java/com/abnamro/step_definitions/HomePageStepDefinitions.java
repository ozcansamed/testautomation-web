package com.abnamro.step_definitions;

import com.abnamro.pages.HomePage;
import com.abnamro.pages.LoginPage;
import com.abnamro.utilities.BrowserUtils;
import com.abnamro.utilities.ConfigurationReader;
import com.abnamro.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.cdimascio.dotenv.Dotenv;

public class HomePageStepDefinitions {
    Dotenv dotenv;
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @Given("user is on the home page with entering {string} and {string}")
    public void user_is_on_the_home_page_with_entering_and(String userEmailAddressEnv, String passwordEnv) {
        dotenv = Dotenv.configure().load(); // load .env for valid user credentials
        Driver.getDriver().get(ConfigurationReader.getProperty("web.abnamro.ci.URL"));
        BrowserUtils.sleep(1);
        String userEmailAddress = System.getenv().getOrDefault(userEmailAddressEnv, dotenv.get(userEmailAddressEnv));
        String password = System.getenv().getOrDefault(passwordEnv, dotenv.get(passwordEnv));

        loginPage.inputUserEmailAddress.sendKeys(userEmailAddress);
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    // ===== AC-1 => User should see “Products” header on the home page ==============

    @Then("user should see {string} header")
    public void user_should_see_header(String expectedHeader) {
        String actualHeader = homePage.productsHeading.getText();
        Assert.assertEquals(actualHeader,expectedHeader);
    }

    // ===== AC-2 => Profile icon must be clickable. =================

    @When("User clicks profile icon")
    public void user_clicks_profile_icon() {
        homePage.profileIcon.click();
    }

    @Then("user sees Sign Out button")
    public void user_sees_sign_out_button() {
       Assert.assertTrue(homePage.signOutIcon.isDisplayed());
    }

    // ====== AC-3 => user should see a text which contains “Lorem ipsum” ========

    @Then("Content contains {string}")
    public void content_contains(String expectedString) {
       Assert.assertTrue(homePage.containerText.getText().contains(expectedString));
    }

    // ====== AC-4 => Validate in the sign out page, user sees given text in footer. ========

    @Then("On the home page, footer is {string}")
    public void on_the_home_page_footer_is(String expectedFooterText) {
        String actualFooterText = loginPage.footerText.getText();
        Assert.assertTrue(actualFooterText.equals(expectedFooterText));
    }

    // ====== AC-5 => Validate user sees "Single Page Application" as the title. ========

    @Then("On the home page, page title is {string}")
    public void on_the_home_page_page_title_is(String expectedTitle) {
        BrowserUtils.verifyTitle(expectedTitle);
    }

    // ====== AC-6 => Validate on the home page, user sees ABN AMRO logo as a background image.========

    @Then("On the home page, background image is {string}")
    public void on_the_home_page_background_image_is(String expectedBackgroundImage) {
        String actualBackgroundImage =loginPage.loginSection.getCssValue("background-image");
        Assert.assertTrue(actualBackgroundImage.contains(expectedBackgroundImage));
    }



}
