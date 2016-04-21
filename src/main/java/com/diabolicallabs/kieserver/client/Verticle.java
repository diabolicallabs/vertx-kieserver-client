package com.diabolicallabs.kieserver.client;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.Base64;

public class Verticle extends AbstractVerticle {

  String encodedCredential;

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    JsonObject serverConfig = config().getJsonObject("kie_server");

    String credential = serverConfig.getString("user") + ":" + serverConfig.getString("password");
    encodedCredential = new String(Base64.getEncoder().encode(credential.getBytes()));

    ProxyHelper.registerService(
      KieServerClientService.class, getVertx(),
      new KieServerClientServiceImpl(getVertx(), serverConfig.getString("host"), serverConfig.getInteger("port"), encodedCredential),
      KieServerClientService.DEFAULT_ADDRESS);

    startFuture.complete();
  }
}
