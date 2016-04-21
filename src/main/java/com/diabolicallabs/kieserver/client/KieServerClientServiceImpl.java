package com.diabolicallabs.kieserver.client;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class KieServerClientServiceImpl implements KieServerClientService {

  String host;
  int port;
  String encodedCredential;
  String urlFragment;
  Vertx vertx;

  public KieServerClientServiceImpl(Vertx vertx, String host, int port, String encodedCredential) {

    this.vertx = vertx;
    this.host = host;
    this.port = port;
    this.encodedCredential = encodedCredential;
    urlFragment = "/kie-server/services/rest/";
  }

  @Override
  public void information(Handler<AsyncResult<KieServerInformation>> handler) {

    vertx
      .createHttpClient()
      .get(port, host, urlFragment + "server",
        responseHandler -> {
          responseHandler.bodyHandler(bodyHandler -> {
            JsonObject response = bodyHandler.toJsonObject();
            if (response.getString("type").equals("SUCCESS")) {
              JsonObject json = response.getJsonObject("result").getJsonObject("kie-server-info");
              handler.handle(Future.succeededFuture(new KieServerInformation(json)));
            } else {
              handler.handle(Future.failedFuture(response.getString("msg")));
            }
          });
        })
      .putHeader("accept", "application/json").putHeader("Authorization", "Basic " + encodedCredential).end();
  }

}
