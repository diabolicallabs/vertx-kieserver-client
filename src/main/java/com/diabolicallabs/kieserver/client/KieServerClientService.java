package com.diabolicallabs.kieserver.client;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;

@ProxyGen
@VertxGen
public interface KieServerClientService {

  static String DEFAULT_ADDRESS = "kie.server.client.service";

  static KieServerClientService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(KieServerClientService.class, vertx, address);
  }

  void information(Handler<AsyncResult<KieServerInformation>> handler);

}
