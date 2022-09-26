package com.abnamro.pages;

import com.abnamro.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (className = "home")
    public WebElement homeHeading;

    @FindBy (className = "products")
    public WebElement productsHeading;

    @FindBy (className = "contact")
    public WebElement contactHeading;

    @FindBy(xpath = "//section[@id='user']")
    public WebElement profileIcon;

    @FindBy(tagName = "span")
    public WebElement signOutIcon;

    @FindBy(xpath = "//div[@class='div-container']/p")
    public WebElement containerText;

    @FindBy(css = "footer > p")
    public WebElement footerText;

}
