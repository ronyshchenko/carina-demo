package com.qaprosoft.carina.demo.gui.pages;

import org.openqa.selenium.WebDriver;

public class ProductPageWithId4 extends AfterLoginPage{
    public ProductPageWithId4(WebDriver driver) {
        super(driver);
        setPageURL("https://www.saucedemo.com/inventory-item.html?id=4");
    }
}
