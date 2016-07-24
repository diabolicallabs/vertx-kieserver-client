package com.diabolicallabs.kie.server;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

@ProxyGen
@VertxGen
public interface KieServerClientTaskService {

  static String DEFAULT_ADDRESS = "kie.server.client.task.service";

  static KieServerClientTaskService createProxy(Vertx vertx, String address) {
    return ProxyHelper.createProxy(KieServerClientTaskService.class, vertx, address);
  }

  void taskActivate(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskClaim(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskStart(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskStop(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskComplete(String encodedCredential, String containerName, Long taskId, JsonObject variables, Handler<AsyncResult<Void>> handler);
  void taskDelegate(String encodedCredential, String containerName, Long taskId, String delegateId, Handler<AsyncResult<Void>> handler);
  void taskExit(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskFail(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskForward(String encodedCredential, String containerName, Long taskId, String userId, Handler<AsyncResult<Void>> handler);
  void taskRelease(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskResume(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskSkip(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskSuspend(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler);
  void taskNominate(String encodedCredential, String containerName, Long taskId, List<String> nominations, Handler<AsyncResult<Void>> handler);

}
