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
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;


public class ProductCardPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public ProductCardPage(WebDriver driver) {
        super(driver);
        setUiLoadedMarker(products);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    @FindBy(xpath = "//*[@class=\"inventory_item_img\"]")
    private By productsImage;

    @FindBy(xpath = "//*[@class=\"inventory_item_name\"]")
    private By productsName ;

    @FindBy(xpath = "//*[@class=\"inventory_item_desc\"]")
    private By productsDesc ;

    @FindBy(xpath = "//*[@class=\"inventory_item_price\"]")
    private By productsPrice ;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private By  select ;

    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[1]")
    private By firstOption ;

    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[2]")
    private By secondOption ;

    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[3]")
    private By thirdOption ;

    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[4]")
    private By fourthOption;

    @FindBy(xpath = "//*/div[@class=\"inventory_item_name\"]")
    private By itemsName;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span\n")
    private ExtendedWebElement products;



//    By products1 = By.xpath("//*[@id=\"header_container\"]/div[2]/span\n");
//    By productsImage1 = By.xpath("//*[@class=\"inventory_item_img\"]");
//    By productsName1 = By.xpath("//*[@class=\"inventory_item_name\"]");
//    By productsDesc1 = By.xpath("//*[@class=\"inventory_item_desc\"]");
//    By productsPrice1 = By.xpath("//*[@class=\"inventory_item_price\"]");
    By buttonCardAdd1 = By.xpath("//div[@class=\"pricebar\"]/button");
//
//    By select1 = By.xpath("//select[@class='product_sort_container']");
//    By firstOption1= By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[1]");
//    By secondOption1 = By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[2]");
//    By thirdOption1 = By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[3]");
//    By fourthOption1 = By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select/option[4]");
//
//    By itemsName1 = By.xpath("//*/div[@class=\"inventory_item_name\"]");
//
//    WebElement selectMenu1;


    public Boolean checkProductsCardElements() throws InterruptedException {
        if (isElementPresent(productsImage) && isElementPresent(productsName) &&
                isElementPresent(productsDesc) && isElementPresent(productsPrice) &&
                isElementPresent(buttonCardAdd1)) {
            return true;
        }
        return false;
    }

    public Boolean checkSelect() throws InterruptedException {
        WebElement selectMenu = getDriver().findElement(select);
        selectMenu.click();
        Thread.sleep(3000);

        return (isElementPresent(firstOption) && isElementPresent(secondOption) &&
                isElementPresent(thirdOption) && isElementPresent(fourthOption));

    }

    public Boolean checkDefaultFilter() throws InterruptedException {
        WebElement selectMenu = getDriver().findElement(select);
        selectMenu.click();
        Thread.sleep(3000);
        String defaultFilter = selectMenu.getAttribute("value");

        WebElement option = getDriver().findElement(secondOption);
        option.click();
        Thread.sleep(3000);

        selectMenu = getDriver().findElement(select);
        selectMenu.click();

        String otherFilter = selectMenu.getAttribute("value");

        option = getDriver().findElement(firstOption);
        option.click();

        if (StringUtils.equals(defaultFilter, "az") && StringUtils.equals(otherFilter, "za")) {
            return true;
        }
        return false;
    }


    public Boolean checkOrderAz() throws InterruptedException {
        List<WebElement> linkList = getDriver().findElements(itemsName);
        Boolean isRightOrder = true;
        for(int i=1; i<6; i++){
            int result = linkList.get(i).getText().compareTo(linkList.get(i-1).getText());
            if (result<0) {
                isRightOrder = false;
            }
        }
        return isRightOrder;
    }

    public Boolean checkOrderZa() throws InterruptedException {
        List<WebElement> linkList = driver.findElements(itemsName);
        Boolean isRightOrder = true;
        for(int i=1; i<6; i++){
            int result = linkList.get(i).getText().compareTo(linkList.get(i-1).getText());
            if (result>0) {
                isRightOrder = false;
            }
        }
        return isRightOrder;
    }

    public boolean isElementPresent(By el) {
        try {
            getDriver().findElement(el);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
