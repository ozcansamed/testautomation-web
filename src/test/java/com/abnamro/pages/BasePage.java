package com.abnamro.pages;

import com.abnamro.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;

    //constructor
    BasePage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

}