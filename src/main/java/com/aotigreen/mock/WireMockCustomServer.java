package com.aotigreen.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

public class WireMockCustomServer {

    public static void main(String[] args){

        //No-args constructor will start on port 8080, no HTTPS
        WireMockConfiguration config = new WireMockConfiguration().port(8089);
        //TODO 关闭回放功能
        config.disableRequestJournal();
        //注册自定义response转换器
        config.extensions(CustomRespTransfer.class);
        config.extensions(CustomRespTransfer1.class);
        //注册 通用response 模版   可以和ResponseTransfer配合使用，但是不可以和ResponeDefintionTransfer配置使用
        config.extensions(new ResponseTemplateTransformer(false));

        //mapping和file文件夹所在位置
        config.usingFilesUnderClasspath("./");
        WireMockServer wireMockServer = new WireMockServer(config);
        wireMockServer.start();

    }
}
