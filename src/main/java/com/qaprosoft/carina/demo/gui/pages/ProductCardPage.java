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

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.components.CardItem;
import com.qaprosoft.carina.demo.gui.components.FilterMenu;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;


public class ProductCardPage extends AfterLoginPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ProductCardPage(WebDriver driver) {
        super(driver);
        setPageURL("https://www.saucedemo.com/inventory.html");
    }

    @FindBy(xpath = "//div[@class=\"inventory_item\"]")
    private List<CardItem> cardItems;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//img")
    private List<ExtendedWebElement> productImages;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//a/div")
    private List<ExtendedWebElement> itemNames;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_desc\"]")
    private List<ExtendedWebElement> productDescriptions;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> productPrices;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//button")
    private List<ExtendedWebElement> addToCardButtons;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private ExtendedWebElement select;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span\n")
    private ExtendedWebElement products;

    @FindBy(xpath = "//select")
    private FilterMenu filterMenu;


    public List<ExtendedWebElement> getFirstCardElement(){
        return List.of(productImages.get(0), itemNames.get(0), productDescriptions.get(0),
                productPrices.get(0), addToCardButtons.get(0));
    }

    public FilterMenu getFilterMenu() {
        return filterMenu;
    }

    public List<CardItem> getCardItems() {
        return cardItems;
    }

    public Boolean checkOrderAz() throws InterruptedException {
        Boolean isRightOrder = true;
        for(int i=1; i<6; i++){
            int result = itemNames.get(i).getText().compareTo(itemNames.get(i-1).getText());
            if (result<0) {
                isRightOrder = false;
            }
        }
        return isRightOrder;
    }

    public Boolean checkOrderZa() throws InterruptedException {
        Boolean isRightOrder = true;
        for(int i=1; i<6; i++){
            int result = itemNames.get(i).getText().compareTo(itemNames.get(i-1).getText());
            if (result>0) {
                isRightOrder = false;
            }
        }
        return isRightOrder;
    }

    public ProductPageWithId4 openFirstProductPage(){
        productImages.get(0).click();
        return new ProductPageWithId4(driver);
    }

    public boolean clickCartButton(int index){
        addToCardButtons.get(index).click();
        return StringUtils.equalsIgnoreCase(addToCardButtons.get(index).getText(), "remove");
    }
}
