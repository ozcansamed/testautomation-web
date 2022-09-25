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
        Driver.getDriver().get(ConfigurationReader.getProperty("web.abnamro.initial.URL"));
        BrowserUtils.sleep(2);
    }

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

        System.out.println("actualPageHeading = " + actualPageHeading);
        System.out.println("page_heading = " + page_heading);

    }


}
