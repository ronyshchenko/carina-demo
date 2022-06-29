package com.qaprosoft.carina.demo.gui.pages;

import com.qaprosoft.carina.demo.gui.components.Header;
import com.qaprosoft.carina.demo.gui.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class AfterLoginPage extends SauceDemoAbstract {

    @FindBy(xpath = "//div[@class=\"primary_header\"]")
    private Header header;

    public AfterLoginPage(WebDriver driver) {
        super(driver);
    }

    public Header getHeader() {
        return header;
    }
}
