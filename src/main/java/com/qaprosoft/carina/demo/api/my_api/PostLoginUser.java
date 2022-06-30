package com.qaprosoft.carina.demo.api.my_api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "https://reqres.in/login", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/user_api/postLogin/rq.json")
@ResponseTemplatePath(path = "api/user_api/postLogin/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PostLoginUser extends AbstractApiMethodV2 {

    public PostLoginUser() {
        super("api/user_api/postLogin/rq.json", "api/user_api/postLogin/rs.json");
        //, "api/users_api/user.properties");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
