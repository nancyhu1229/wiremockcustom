package com.aotigreen.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.trafficlistener.ConsoleNotifyingWiremockNetworkTrafficListener;
import com.github.tomakehurst.wiremock.recording.RecordSpecBuilder;
import com.tairan.qa.mock.webhooks.Webhooks;
//import org.wiremock.webhooks.Webhooks;

/**
 * nancy.hu
 * 20181019
 */
public class WireMockCustomServer {

    public static void main(String[] args){

        //No-args constructor will start on port 8080, no HTTPS
        WireMockConfiguration config = new WireMockConfiguration().port(8089);

        //关闭记录请求信息。不做请求记录
        config.disableRequestJournal();
        //注册自定义response转换器
        config.extensions(CustomRespTransfer.class);
        config.extensions(CustomRespTransfer2.class);

        config.extensions(CustomRespTransfer1.class);

        //注册webhook，调用异步回调的功能
        config.extensions(new Webhooks());
        //注册 通用response 模版   可以和ResponseTransfer配合使用，但是不可以和ResponeDefintionTransfer配置使用
        config.extensions(new ResponseTemplateTransformer(false));

        //注册webhook，调用异步回调的功能
        config.extensions(new Webhooks());

        //mapping和file文件夹所在位置
        config.usingFilesUnderClasspath("./");

        //监听row traffic
//        config.networkTrafficListener(new ConsoleNotifyingWiremockNetworkTrafficListener());

        config.networkTrafficListener();

        config.notifier(new ConsoleNotifier(true));
        WireMockServer wireMockServer = new WireMockServer(config);


//        wireMockServer.snapshotRecord(
//                new RecordSpecBuilder().makeStubsPersistent(true));
        wireMockServer.start();
    }
}
