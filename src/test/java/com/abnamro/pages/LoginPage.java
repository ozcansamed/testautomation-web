package com.abnamro.pages;

import com.abnamro.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public String currentURL;

    @FindBy(id = "email") // @FindBy(input#email) // @FindBy(xpath = "//input[@name='email']")
    public WebElement inputUserEmailAddress;

    @FindBy(id = "password") // @FindBy(input#password) // @FindBy(xpath = "//input[@type='password']")
    public WebElement inputPassword;

    @FindBy (name = "email")
    public WebElement validationMessage_InputUserEmail;

    @FindBy (name = "password")
    public WebElement validationMessage_InputPassword;

    @FindBy(xpath = "//input[@value='LOGIN']")
    public WebElement loginButton;

    @FindBy(tagName = "h1")
    public WebElement loginPageHeader;

    @FindBy(css = "footer > p")
    public WebElement footerText;

    @FindBy (className = "login")
    public WebElement loginSection;

}