package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.APIMethodPoller;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.api.PostUserMethod;
import com.qaprosoft.carina.demo.api.my_api.*;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.zebrunner.agent.core.registrar.TestRunRegistrar.LOGGER;

public class MyApiTest implements IAbstractTest {
    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeather() {
        GetCityWeather getCityWeather = new GetCityWeather();
        getCityWeather.callAPIExpectSuccess();
        getCityWeather.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCityWeather.validateResponseAgainstSchema("api/weather/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeatherByZipCode() {
        GetWeatherByZipCode getWeatherByZipCode = new GetWeatherByZipCode();
        getWeatherByZipCode.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByZipCode.callAPI();
        getWeatherByZipCode.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherByZipCode.validateResponseAgainstSchema("api/weather/_getByZipCode/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeatherById() {
        GetWeatherById getWeatherById = new GetWeatherById();
        getWeatherById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherById.callAPI();
        getWeatherById.validateResponseAgainstSchema("api/weather/_getById/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void testGetWeatherByGeoCoordinates() {
        GetWeatherByGeoCoordinates getWeatherByGeoCoordinates = new GetWeatherByGeoCoordinates();
        getWeatherByGeoCoordinates.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByGeoCoordinates.callAPI();
        getWeatherByGeoCoordinates.validateResponseAgainstSchema("api/weather/_getByGeoCoordinates/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "cats")
    public void testGetCatFact(){
        GetCatFactMethod getCatFactMethod = new GetCatFactMethod();
        getCatFactMethod.callAPIExpectSuccess();
        getCatFactMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getCatFactMethod.validateResponseAgainstSchema("api/cats/rs.schema");

    }

     @Test()
    public void testGetListReqEmployees(){
        GetEmployees api = new GetEmployees();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/my_api/rs.schema");

    }

    @Test()
    public void testGetListUsers(){
        GetUserList api = new GetUserList();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/user_api/_get_list/rs.schema");

    }

    @Test()
    public void testGetUsers(){
        GetUser api = new GetUser();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/user_api/_get/rs.schema");
    }

    @Test()
    public void GetPandasFactTest(){
        GetPandasFactMethod getPandasFactMethod = new GetPandasFactMethod();
        getPandasFactMethod.callAPIExpectSuccess();
        getPandasFactMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getPandasFactMethod.validateResponseAgainstSchema("api/animals/rs.schema");

    }

    @Test()
    public void testPutReqUser() throws Exception {
        PutUser api = new PutUser();
        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> counter.get() == 2)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(10, ChronoUnit.SECONDS)
                .execute();
        api.validateResponse();
    }

    @Test()
    public void testDeleteUsers() {
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.setProperties("api/users/user.properties");
        deleteUser.callAPIExpectSuccess();
        deleteUser.validateResponse();
    }}
