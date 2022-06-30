package com.qaprosoft.carina.demo.api.my_api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "http://api.openweathermap.org/geo/1.0/direct?q=London&limit=1&appid=3fd3fad29dd259a54d93fcfdec513153", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/weather/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)

public class GetCityWeather extends AbstractApiMethodV2 {
    public GetCityWeather() {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
