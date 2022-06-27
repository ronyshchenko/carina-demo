/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qaprosoft.carina.demo.gui.pages;

import java.lang.invoke.MethodHandles;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.demo.gui.mygui.pages.InventoryPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;


public class SauceLoginPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public SauceLoginPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(loginPageMarker);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]")
    private ExtendedWebElement loginPageMarker;

    @FindBy(xpath = "//*[@id=\"user-name\"]")
    private ExtendedWebElement userInput;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[1]/div[1]/div/form/div[2]/input")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div/div[1]")
    private ExtendedWebElement userNameStr;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]")
    private ExtendedWebElement passwordStr;

    By products = By.xpath("//*[@id=\"header_container\"]/div[2]/span\n");

    public Boolean checkCoordinates() {
        if ((userInput.getLocation().getY() < passwordInput.getLocation().getY()) &&
                (passwordInput.getLocation().getY() < loginButton.getLocation().getY())) {
            return true;
        }
        return false;
    }

    public Boolean authorization() throws InterruptedException {
        WebElement userInput = getDriver().findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement passwordInput = getDriver().findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div[1]/div/form/div[2]/input"));
        WebElement loginButton = getDriver().findElement(By.xpath("//*[@id=\"login-button\"]"));

        WebElement userNameStr = getDriver().findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div[1]"));
        WebElement passwordStr = getDriver().findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]"));
        userInput.sendKeys(userNameStr.getText().split(":", 2)[1].split("locked", 2)[0]);
        passwordInput.sendKeys(passwordStr.getText().split(":", 2)[1]);
        loginButton.click();
        Thread.sleep(3000);

        try {
            getDriver().findElement(products);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ProductCardPage logInAsStandardUser() {
        userInput.type("standard_user");
        passwordInput.type("secret_sauce");
        loginButton.click();
        return new ProductCardPage(driver);
    }

    public ProductCardPage enterWrightDataAndClick(String username, String password){
        this.userInput.type(username);
        this.passwordInput.type(password);
        loginButton.click();
        return new ProductCardPage(driver);
    }

}
