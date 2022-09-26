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


public class signOutStepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @Given("user is on the home page with entering {string} and {string}.")
    public void user_is_on_the_home_page_with_entering_and(String userEmailAddressEnv, String passwordEnv) {
        Driver.getDriver().get(ConfigurationReader.getProperty("web.abnamro.ci.URL"));
        BrowserUtils.sleep(1);
        String userEmailAddress = System.getenv(userEmailAddressEnv);
        String password = System.getenv(passwordEnv);

        loginPage.inputUserEmailAddress.sendKeys(userEmailAddress);
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    // ===== AC-1 => User can sign out by using sign out button inside profile info and ends up in login page. ==============

    @When("user clicks sign out button.")
    public void user_clicks_sign_out_button() {
        homePage.profileIcon.click();
        BrowserUtils.waitForVisibility(homePage.signOutIcon, 1);
        homePage.signOutIcon.click();
        BrowserUtils.sleep(2);
    }

    @Then("user should be able to sign out and lands on the login page")
    public void user_should_be_able_to_sign_out_and_lands_on_the_login_page() {
        String loginButtonValue = loginPage.loginButton.getAttribute("value");
        Assert.assertTrue("Actual and expected page headings didn't match", loginButtonValue.equalsIgnoreCase("login"));
        System.out.println("loginButtonValue = " + loginButtonValue);
    }

    // ===== AC-2 => User can not go to the home page again by clicking the step back button after successfully signed out. ==============

    @When("clicks step back button")
    public void clicks_step_back_button() {
        Driver.getDriver().navigate().back();
        BrowserUtils.sleep(2);
    }

    @Then("user shouldn't be able to land on home page")
    public void user_shouldn_t_be_able_to_land_on_home_page() {
        Assert.assertNotEquals(Driver.getDriver().getTitle(), "Single Page Application");
    }

    // ===== AC-3 => The user must be signed out if the user is away from keyboard for 1 minutes (if the user does not do any  mouse or keyboard action for 3 minutes) ==============

    @When("user is away from keyboard for {int} minutes")
    public void user_is_away_from_keyboard_for_minutes(Integer int1) {
        BrowserUtils.sleep(65);
    }

    @Then("user must be signed out")
    public void user_must_be_signed_out() {
        String actualPageHeading = homePage.homeHeading.getText();
        Assert.assertFalse("User must have been signed out, the user is away from keyboard for 1 minutes", actualPageHeading.contains("Home"));
    }


}
