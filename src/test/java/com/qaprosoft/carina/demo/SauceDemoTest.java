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
package com.qaprosoft.carina.demo;

import java.lang.invoke.MethodHandles;
import java.util.List;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.gui.components.CardItem;
import com.qaprosoft.carina.demo.gui.pages.BasketPage;
import com.qaprosoft.carina.demo.gui.pages.ProductCardPage;
import com.qaprosoft.carina.demo.gui.pages.ProductPageWithId4;
import com.qaprosoft.carina.demo.gui.pages.SauceLoginPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.testng.asserts.SoftAssert;

public class SauceDemoTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    public void loadSiteСheckPositions() {
        SoftAssert softAssert = new SoftAssert();
        SauceLoginPage sauceLoginPage = openPage();
        Assert.assertTrue(sauceLoginPage.checkCoordinates(), "Positions isn`t write");
        softAssert.assertAll();
    }

    @Test()
    public void loginUserTest() {
        SoftAssert softAssert = new SoftAssert();
        SauceLoginPage sauceLoginPage = openPage();
        ProductCardPage productCardPage = sauceLoginPage.enterWrightDataAndClick("standard_user", "secret_sauce");
        Assert.assertTrue(productCardPage.isPageOpened(), "Product page not opened");
        productCardPage.openURL(sauceLoginPage.getPageURL());
        productCardPage = sauceLoginPage.enterWrightDataAndClick("performance_glitch_user", "secret_sauce");
        Assert.assertTrue(productCardPage.isPageOpened(), "Product page not opened");
        softAssert.assertAll();
    }


    @Test()
    public void verifyProductItemCard() throws InterruptedException {
        ProductCardPage productCardPage = loginAsStandardUser();
        List<ExtendedWebElement> elementList = productCardPage.getFirstCardElement();
        SoftAssert softAssert = new SoftAssert();
        for (ExtendedWebElement element : elementList) {
            softAssert.assertTrue(element.isElementPresent(), element.getName() + " not present");
        }
        softAssert.assertAll();
    }

    @Test()
    public void testCardNamesTest() {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        List<CardItem> cards = productCardPage.getCardItems();
        List<String> expectedNames = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
        int i = 0;
        for(CardItem card: cards) softAssert.assertTrue(StringUtils.equalsIgnoreCase(card.getNameText(),
                expectedNames.get(i++)));
        softAssert.assertAll();
    }

    @Test()
    public void verifyFilterMenuTest() {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        List<ExtendedWebElement> elementList = productCardPage.getFilterMenu().getOptions();
        for (ExtendedWebElement element : elementList) {
            softAssert.assertTrue(element.isElementPresent(), "Some elements not present");
        }
        softAssert.assertAll();
    }

    @Test()
    public void checkFilterMenuTest() {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        List<ExtendedWebElement> elementList = productCardPage.getFilterMenu().getOptions();
        List<String> expectedNames = List.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)");
        int i = 0;
        for(ExtendedWebElement element : elementList) softAssert.assertTrue(StringUtils.equals(element.getText(),
                expectedNames.get(i++)));
        softAssert.assertAll();
    }

    @Test()
    public void verifyDropDownMenuTest() {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productCardPage.getFilterMenu().isSelected("name (a to z)"),
                "A-z is not selected");
        softAssert.assertTrue(productCardPage.getFilterMenu().select(1), "Z to a in not selected");
        softAssert.assertTrue(productCardPage.getFilterMenu().isSelected("name (z to a)"),
                "Z to a in not selected");
        softAssert.assertAll();
    }

    @Test()
    public void checkProductsOrderAz() throws InterruptedException {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productCardPage.getFilterMenu().isSelected("name (a to z)"),
                "A-z is not selected");
        softAssert.assertTrue(productCardPage.checkOrderAz());
        softAssert.assertAll();
    }

    @Test()
    public void checkProductsOrderZa() throws InterruptedException {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productCardPage.getFilterMenu().select(1), "Z to a in not selected");
        softAssert.assertTrue(productCardPage.getFilterMenu().isSelected("name (z to a)"),
                "A-z is not selected");
        softAssert.assertTrue(productCardPage.checkOrderZa());
        softAssert.assertAll();
    }

    @Test()
    public void basketQuantityTest() {
        ProductCardPage productCardPage = loginAsStandardUser();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productCardPage.clickCartButton(0));
        softAssert.assertTrue(productCardPage.clickCartButton(2));
        softAssert.assertAll();

        BasketPage basketPage = productCardPage.getHeader().goToBasket();
        Assert.assertTrue(basketPage.isPageOpened(), "Basket page didn`t opened");
        Assert.assertEquals(basketPage.quantityOfCardItems(), 2);
    }

    @Test()
    public void openFirstCardTest() {
        ProductCardPage productCardPage = loginAsStandardUser();
        ProductPageWithId4 productPageWithId4 = productCardPage.openFirstProductPage();
        Assert.assertTrue(productPageWithId4.isPageOpened(), "Product page was no open");
    }


    private SauceLoginPage openPage() {
        SauceLoginPage sauceLoginPage = new SauceLoginPage(getDriver());
        sauceLoginPage.open();
        Assert.assertTrue(sauceLoginPage.isPageOpened(), "Login page not opened");
        return sauceLoginPage;
    }

    private ProductCardPage loginAsStandardUser() {
        SauceLoginPage sauceLoginPage = openPage();
        ProductCardPage productCardPage = sauceLoginPage.logInAsStandardUser();
        Assert.assertTrue(productCardPage.isPageOpened(), "Inventory page not opened");
        LOGGER.info("User login success, inventory page is opened");
        return productCardPage;
    }
}
