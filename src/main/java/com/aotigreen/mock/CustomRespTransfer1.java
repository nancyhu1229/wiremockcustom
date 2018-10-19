package com.aotigreen.mock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

/**
 * nancy.hu
 * 20181019
 */
public class CustomRespTransfer1 extends ResponseDefinitionTransformer {

    public String getName() {
        return "CustomRespTransfer1";
    }

    @Override
    public boolean applyGlobally() {
        return false;
    }

    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource fileSource, Parameters parameters) {
        ResponseDefinitionBuilder build = new ResponseDefinitionBuilder();
        build.withStatus(200);
        build.withBody(responseDefinition.getBody() + " ====hello my transfer 1 ");
//        TODO 增加自己的实现
        return build.build();
    }
}
