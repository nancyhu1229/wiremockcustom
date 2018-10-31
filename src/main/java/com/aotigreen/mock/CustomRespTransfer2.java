package com.aotigreen.mock;

import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;

/**
 * nancy.hu
 * 20181019
 */
public class CustomRespTransfer2 extends ResponseTransformer {
    public Response transform(Request request, Response response, FileSource fileSource, Parameters parameters) {
        Response.Builder builder = new Response.Builder();
        builder.status(200);
        //TODO 增加自己的实现
        builder.body(response.getBodyAsString() + " ====my transfer 2");
        return builder.build();
    }

    public String getName() {
        return "My-Transfer2";
    }

    @Override
    public boolean applyGlobally() {
        return false;
    }
}
