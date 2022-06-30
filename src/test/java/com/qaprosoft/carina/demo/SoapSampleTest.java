//package com.qaprosoft.carina.demo;
//
//import com.qaprosoft.apitools.validation.XmlCompareMode;
//import com.qaprosoft.carina.core.foundation.IAbstractTest;
//import com.qaprosoft.carina.demo.gui.pages.BasketPage;
//import com.qaprosoft.carina.demo.gui.pages.ProductCardPage;
//import com.qaprosoft.carina.demo.gui.pages.ProductPageWithId4;
//import com.qaprosoft.carina.demo.soap.AddIntegerMethod;
//import com.qaprosoft.carina.demo.soap.LookupCityMethod;
//import io.restassured.path.xml.XmlPath;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//public class SoapSampleTest implements IAbstractTest {
//
//    @Test
//    public void testAddInteger() {
//        AddIntegerMethod soap = new AddIntegerMethod();
//        soap.setProperties("api/soap/soap.properties");
//
//        Response response = soap.callAPIExpectSuccess();
//        XmlPath rsBody = XmlPath.given(response.asString());
//        Integer actualResult = rsBody.getInt("AddIntegerResult");
//        Integer expectedResult = Integer.valueOf(soap.getProperties().getProperty("result"));
//        Assert.assertEquals(actualResult, expectedResult);
//    }
//
//    @Test
//    public void testLookupCity() {
//        LookupCityMethod soap = new LookupCityMethod();
//        soap.setProperties("api/soap/soap.properties");
//
//        soap.callAPIExpectSuccess();
//        soap.validateXmlResponse(XmlCompareMode.STRICT);
//    }
//
//    @Test()
//    public void basketQuantityTest() {
//        ProductCardPage productCardPage = loginAsStandardUser();
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(productCardPage.clickCartButton(0));
//        softAssert.assertTrue(productCardPage.clickCartButton(2));
//        softAssert.assertAll();
//
//        BasketPage basketPage = productCardPage.getHeader().goToBasket();
//        Assert.assertTrue(basketPage.isPageOpened(), "Basket page didn`t opened");
//        Assert.assertEquals(basketPage.quantityOfCardItems(), 2);
//    }
//
//    @Test()
//    public void openFirstCardTest() {
//        ProductCardPage productCardPage = loginAsStandardUser();
//        ProductPageWithId4 productPageWithId4 = productCardPage.openFirstProductPage();
//        Assert.assertTrue(productPageWithId4.isPageOpened(), "Product page was no open");
//    }
//
//}