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
import java.util.ArrayList;
import java.util.List;

import com.qaprosoft.carina.demo.gui.pages.ProductCardPage;
import com.qaprosoft.carina.demo.gui.pages.SauceLoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.zebrunner.agent.core.annotation.TestLabel;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.gui.components.FooterMenu;
import com.qaprosoft.carina.demo.gui.components.compare.ModelSpecs;
import com.qaprosoft.carina.demo.gui.components.compare.ModelSpecs.SpecType;
import com.qaprosoft.carina.demo.gui.pages.CompareModelsPage;
import com.qaprosoft.carina.demo.gui.pages.HomePage;

public class SauceDemoTest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    SauceLoginPage sauceLoginPage = null;
    ProductCardPage productCardPage = null;

    @BeforeSuite
    public void startDriver() {
        sauceLoginPage = new SauceLoginPage(getDriver());
        productCardPage = new ProductCardPage(getDriver());
    }

    @Test(priority = 1)
    public void testOpenPage1() {
        sauceLoginPage.open();
        Assert.assertTrue(sauceLoginPage.isPageOpened(), "Home page is not opened");
    }

    @Test(priority = 2)
    public void checkOrder() throws InterruptedException {
        Thread.sleep(3000);
        Boolean existenceOrder = sauceLoginPage.checkCoordinates();
        Boolean expectOrder = true;
        Assert.assertEquals(existenceOrder, expectOrder);
    }

    @Test(priority = 3)
    public void loginning() throws InterruptedException {
        Thread.sleep(3000);
        Boolean existenceAutorization = sauceLoginPage.authorization();
        Boolean expectAutorization = true;
        Assert.assertEquals(existenceAutorization, expectAutorization);
    }

    @Test(priority = 4)
    public void productCardCheck() throws InterruptedException {
        Thread.sleep(3000);
        Boolean existenceProduct = productCardPage.checkProductsCardElements();
        Boolean expectProduct = true;
        Assert.assertEquals(existenceProduct, expectProduct);
    }

    @Test(priority = 5)
    public void select() throws InterruptedException {
        Thread.sleep(5000);
        Boolean existenceProduct = productCardPage.checkSelect();
        Boolean expectProduct = true;
        Assert.assertEquals(existenceProduct, expectProduct);
    }

    @Test(priority = 6)
    public void selectDefaultFilter() throws InterruptedException {
        Thread.sleep(5000);
        Boolean existenceProduct = productCardPage.checkDefaultFilter();
        Boolean expectProduct = true;
        Assert.assertEquals(existenceProduct, expectProduct);
    }

    @Test(priority = 7)
    public void checkProductsOrderAz() throws InterruptedException {
        Boolean existenceOrder = productCardPage.checkOrderAz();
        Boolean expectOrder = true;
        Assert.assertEquals(existenceOrder, expectOrder);
    }
}
