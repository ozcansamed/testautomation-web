package com.abnamro.step_definitions;

import com.abnamro.pages.HomePage;
import com.abnamro.pages.LoginPage;
import com.abnamro.utilities.BrowserUtils;
import com.abnamro.utilities.ConfigurationReader;
import com.abnamro.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
    Actions action = new Actions(Driver.getDriver());

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("web.abnamro.improved.URL"));
        BrowserUtils.sleep(1);
    }


    // ======= AC-1 => Positive Scenario => User should be able to log in with valid credentials==========

    @When("user enters {string} and {string} and logins")
    public void user_enters_and_and_logins(String userEmailAddressEnv, String passwordEnv) {
        String userEmailAddress = System.getenv(userEmailAddressEnv);
        String password = System.getenv(passwordEnv);

        loginPage.inputUserEmailAddress.sendKeys(userEmailAddress);
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    @Then("user lands on the {string}")
    public void user_lands_on_the(String page_heading) {
        wait.until(ExpectedConditions.visibilityOf(homePage.homeHeading));
        String actualPageHeading = homePage.homeHeading.getText();
        Assert.assertTrue("Actual and expected page headings didn't match", actualPageHeading.contains(page_heading));
    }

    // ==== AC-1 => Negative Scenario => User shouldn't be able to log in with invalid credentials=========

    @When("user enters invalid {string} or invalid {string} and clicks login button")
    public void user_enters_invalid_or_invalid_and_clicks_login_button(String userEmailAddress, String password) {
        loginPage.inputUserEmailAddress.sendKeys(userEmailAddress);
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    @Then("user cannot login and still on Login Page")
    public void user_cannot_login_and_still_on_login_page() {
        String loginButtonValue = loginPage.loginButton.getAttribute("value");
        Assert.assertTrue("Actual and expected page headings didn't match", loginButtonValue.equalsIgnoreCase("login"));
        System.out.println("loginButtonValue = " + loginButtonValue);
    }

    // === AC-1 => Negative Scenario => System shouldn't allow users to access the application without providing credentials====

    @When("user copies the URL")
    public void user_copies_the_url() {
        loginPage.currentURL = Driver.getDriver().getCurrentUrl();
        System.out.println("loginPage.currentURL = " + loginPage.currentURL);
    }

    @When("user logouts")
    public void user_logouts() {
        BrowserUtils.sleep(2);
        homePage.profileIcon.click();
        BrowserUtils.sleep(2);
        homePage.signOutIcon.click();
        BrowserUtils.sleep(2);
    }

    @When("user pastes the URL to browser and enters login without authentication")
    public void user_pastes_the_url_to_browser_and_enters_login_without_authentication() {
        Driver.getDriver().get(loginPage.currentURL);
        loginPage.loginButton.click();
    }

    @Then("user is still on the login page")
    public void user_is_still_on_the_login_page() {
        String loginButtonValue = loginPage.loginButton.getAttribute("value");
        Assert.assertTrue("Actual and expected page headings didn't match", loginButtonValue.equalsIgnoreCase("login"));
        System.out.println("loginButtonValue = " + loginButtonValue);
    }

    // ======== AC-2 => "Please fill out this field." message should be displayed for empty password field ========
    // ========  PS  => I added this feature in the given JavaScript code and tested

    @When("user enters only username {string} and tries to login")
    public void user_enters_only_username_and_tries_to_login(String userEmail) {
        loginPage.inputUserEmailAddress.sendKeys(userEmail);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    @Then("user gets {string} for password")
    public void user_gets_for_password(String expectedWarningMessage) {
        String actualWarningMessage = loginPage.validationMessage_InputPassword.getAttribute("validationMessage");
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
        System.out.println("actualWarningMessage = " + actualWarningMessage);
        BrowserUtils.sleep(2);
    }

    @When("user enters only password {string} and tries to login")
    public void user_enters_only_password_and_tries_to_login(String password) {
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    @Then("user gets {string} for username")
    public void user_gets_for_username(String expectedWarningMessage) {
        String actualWarningMessage = loginPage.validationMessage_InputUserEmail.getAttribute("validationMessage");
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
    }

    // ======== AC-3 => Validate the Password text entered into the 'Password' field is toggled to hide its visibility  ========

    @Then("password field is toggled to hide")
    public void password_field_is_toggled_to_hide() {
        boolean isPasswordHidden = loginPage.inputPassword.getAttribute("type").equalsIgnoreCase("password");
        Assert.assertTrue(isPasswordHidden);
    }


    // ======== AC-4 => User "E-mail address" field must contain a '@' character.  ========

    @Then("{string} must contain a {string} character")
    public void must_contain_a_character(String userEmailAddressEnv, String givenChar) {
        String userEmailAddress = System.getenv(userEmailAddressEnv);
        Assert.assertTrue("User email must contain '@' character", userEmailAddress.contains(givenChar) );
    }

    // ======== AC-5 => Validate user sees placeholder in user_email_address and in password fields  ========

    @Then("User e-mail input field contains {string} as a placeholder")
    public void user_e_mail_input_field_contains_as_a_placeholder(String givenPlaceholder) {
        String actualEmailAddressPlaceholder = loginPage.inputUserEmailAddress.getAttribute("placeholder");
        Assert.assertTrue(actualEmailAddressPlaceholder.equals(givenPlaceholder));
    }

    @Then("User password field contains {string} as a placeholder")
    public void user_password_field_contains_as_a_placeholder(String givenPlaceholder) {
        String actualPasswordPlaceholder = loginPage.inputPassword.getAttribute("placeholder");
        Assert.assertTrue(actualPasswordPlaceholder.equals(givenPlaceholder));
    }

    // ======== AC-6 => Validate user sees "Single Page Application" as the title.  ========

    @Then("Page title is {string}")
    public void page_title_is(String givenTitle) {
        BrowserUtils.verifyTitle(givenTitle);
    }

    // ======== AC-7 => Validate in the login page, user sees given text above credentials area. ========

    @Then("In login page, user sees {string}")
    public void in_login_page_user_sees(String givenText) {
       String actualText = loginPage.loginPageHeader.getText();
       Assert.assertTrue(actualText.equals(givenText));
    }

    // ======== AC-8 => Validate in the login page, user sees given text in footer. ========

    @Then("Footer is {string}")
    public void footer_is(String givenFooterText) {
        String actualFooterText = loginPage.footerText.getText();
        Assert.assertTrue(actualFooterText.equals(givenFooterText));
        System.out.println("actualFooterText = " + actualFooterText);
        System.out.println("givenFooterText = " + givenFooterText);
    }















}
