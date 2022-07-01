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
    }

    @FindBy(xpath = "//*[@id=\"user-name\"]")
    private ExtendedWebElement userInput;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    private ExtendedWebElement loginButton;

    public Boolean checkCoordinates() {
        return ((userInput.getLocation().getY() < passwordInput.getLocation().getY()) &&
                (passwordInput.getLocation().getY() < loginButton.getLocation().getY()));
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
