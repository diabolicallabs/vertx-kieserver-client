package com.diabolicallabs.kie.server;

import com.diabolicallabs.kie.server.service.KieServerClientService;
import com.diabolicallabs.kie.server.service.KieServerClientServiceImpl;
import com.diabolicallabs.kie.server.service.KieServerClientTaskService;
import com.diabolicallabs.kie.server.service.KieServerClientTaskServiceImpl;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.serviceproxy.ProxyHelper;

public class Verticle extends AbstractVerticle {

  String encodedCredential;

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    JsonObject serverConfig = config().getJsonObject("kie_server");

    encodedCredential = Credential.encode(serverConfig.getString("user"), serverConfig.getString("password"));

    ProxyHelper.registerService(
      KieServerClientService.class, getVertx(),
      new KieServerClientServiceImpl(getVertx(), serverConfig.getString("host"), serverConfig.getInteger("port"), encodedCredential),
      KieServerClientService.DEFAULT_ADDRESS);

    ProxyHelper.registerService(
      KieServerClientTaskService.class, getVertx(),
      new KieServerClientTaskServiceImpl(getVertx(), serverConfig.getString("host"), serverConfig.getInteger("port")),
      KieServerClientTaskService.DEFAULT_ADDRESS);

    startFuture.complete();
  }
}
