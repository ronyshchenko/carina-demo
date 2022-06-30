package com.qaprosoft.carina.demo.api.my_api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "https://reqres.in/api/users?page=2", methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/user_api/_get_list/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)

public class GetUserList extends AbstractApiMethodV2 {
    public GetUserList() {
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
    }
}
