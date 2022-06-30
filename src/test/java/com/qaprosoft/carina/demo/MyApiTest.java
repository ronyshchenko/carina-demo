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
    public void testDeleteUsers() {
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.setProperties("api/users/user.properties");
        deleteUser.callAPIExpectSuccess();
        deleteUser.validateResponse();
    }
    }
