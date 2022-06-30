package com.qaprosoft.carina.demo.api.my_api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

import java.util.Properties;

@Endpoint(url = "https://some-random-api.ml/animal/panda", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/animals/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)

public class GetPandasFactMethod extends AbstractApiMethodV2 {

    public GetPandasFactMethod() {
        super(null, "api/animals/rs.json", new Properties());
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
