package com.abnamro.pages;

import com.abnamro.utilities.ConfigurationReader;
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

}
