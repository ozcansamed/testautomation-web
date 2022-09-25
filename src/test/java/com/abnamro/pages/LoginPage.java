package com.abnamro.pages;

import com.abnamro.utilities.ConfigurationReader;
import com.abnamro.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "email") // @FindBy(input#email) // @FindBy(xpath = "//input[@name='email']")
    public WebElement inputUserEmailAddress;

    @FindBy(id = "password") // @FindBy(input#password) // @FindBy(xpath = "//input[@type='password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//input[@type='button']") // @FindBy(id = "login") // @FindBy(input#login)
    public WebElement loginButton;

    @FindBy(tagName = "h1")
    public WebElement loginPageHeader;

    @FindBy(tagName = "footer")
    public WebElement footerText;

    @FindBy (className = "login")
    public WebElement loginSection;


}