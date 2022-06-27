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
import com.qaprosoft.carina.demo.gui.pages.ProductCardPage;
import com.qaprosoft.carina.demo.gui.pages.SauceLoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import org.testng.asserts.SoftAssert;

public class SauceDemoTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    SauceLoginPage sauceLoginPage = null;
    ProductCardPage productCardPage = null;

    @Test()
    public void checkPositions() {
        SauceLoginPage sauceLoginPage = openPage();
        Assert.assertTrue(sauceLoginPage.checkCoordinates(), "Positions isn`t write");
    }

    @Test()
    public void loginUserTest() {
        SauceLoginPage sauceLoginPage = openPage();
        SoftAssert softAssert = new SoftAssert();
        ProductCardPage productCardPage = sauceLoginPage.enterWrightDataAndClick("standard_user", "secret_sauce");
        softAssert.assertTrue(productCardPage.isPageOpened());
//        productCardPage.openURL(sauceLoginPage.getPageURL());
//        productCardPage = sauceLoginPage.enterWrightDataAndClick("problem_user", "secret_sauce");
//        softAssert.assertTrue(productCardPage.isPageOpened());
//        productCardPage.openURL(sauceLoginPage.getPageURL());
//        productCardPage = sauceLoginPage.enterWrightDataAndClick("performance_glitch_user", "secret_sauce");
//        softAssert.assertTrue(productCardPage.isPageOpened());
//        productCardPage.openURL(sauceLoginPage.getPageURL());
        //softAssert.assertAll();
    }

    @Test()
    public void verifyProductItemCard() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        SauceLoginPage sauceLoginPage = openPage();
        ProductCardPage productCardPage = loginAsStandardUser();
        productCardPage = sauceLoginPage.enterWrightDataAndClick("standard_user", "secret_sauce");
        Boolean existenceProduct = productCardPage.checkProductsCardElements();
        Assert.assertTrue(existenceProduct, "Page don't have elements");
        softAssert.assertAll();
    }

    @Test()
    public void select() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        SauceLoginPage sauceLoginPage = openPage();
        ProductCardPage productCardPage = sauceLoginPage.enterWrightDataAndClick("standard_user", "secret_sauce");
        Boolean existenceProduct = productCardPage.checkSelect();
        Boolean expectProduct = true;
        Assert.assertEquals(existenceProduct, expectProduct);
    }

//    @BeforeSuite
//    public void startDriver() {
//        sauceLoginPage = new SauceLoginPage(getDriver());
//        productCardPage = new ProductCardPage(getDriver());
//    }

//    @Test(priority = 1)
//    public void testOpenPage1() {
//        sauceLoginPage.open();
//        Assert.assertTrue(sauceLoginPage.isPageOpened(), "Home page is not opened");
//    }
//
//    @Test(priority = 2)
//    public void checkOrder() throws InterruptedException {
//        Thread.sleep(3000);
//        Boolean existenceOrder = sauceLoginPage.checkCoordinates();
//        Boolean expectOrder = true;
//        Assert.assertEquals(existenceOrder, expectOrder);
//    }
//
//    @Test(priority = 3)
//    public void loginning() throws InterruptedException {
//        Thread.sleep(3000);
//        Boolean existenceAutorization = sauceLoginPage.authorization();
//        Boolean expectAutorization = true;
//        Assert.assertEquals(existenceAutorization, expectAutorization);
//    }
//
//    @Test(priority = 4)
//    public void productCardCheck() throws InterruptedException {
//        Thread.sleep(3000);
//        Boolean existenceProduct = productCardPage.checkProductsCardElements();
//        Boolean expectProduct = true;
//        Assert.assertEquals(existenceProduct, expectProduct);
//    }
//
//    @Test(priority = 5)
//    public void select() throws InterruptedException {
//        Thread.sleep(5000);
//        Boolean existenceProduct = productCardPage.checkSelect();
//        Boolean expectProduct = true;
//        Assert.assertEquals(existenceProduct, expectProduct);
//    }
//
//    @Test(priority = 6)
//    public void selectDefaultFilter() throws InterruptedException {
//        Thread.sleep(5000);
//        Boolean existenceProduct = productCardPage.checkDefaultFilter();
//        Boolean expectProduct = true;
//        Assert.assertEquals(existenceProduct, expectProduct);
//    }
//
//    @Test(priority = 7)
//    public void checkProductsOrderAz() throws InterruptedException {
//        Boolean existenceOrder = productCardPage.checkOrderAz();
//        Boolean expectOrder = true;
//        Assert.assertEquals(existenceOrder, expectOrder);
//    }

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
