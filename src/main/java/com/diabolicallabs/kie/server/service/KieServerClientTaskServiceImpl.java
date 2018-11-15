package com.diabolicallabs.kie.server.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.List;
import java.util.StringJoiner;

public class KieServerClientTaskServiceImpl implements KieServerClientTaskService {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  String host;
  int port;
  String urlPrefix;
  Vertx vertx;

  public KieServerClientTaskServiceImpl(Vertx vertx, String host, int port) {

    this.vertx = vertx;
    this.host = host;
    this.port = port;
    urlPrefix = "/kie-server/services/rest/";
  }

  private void processAction(String encodedCredential, String sUrl, Handler<AsyncResult<Void>> handler) {

    vertx
      .createHttpClient()
      .put(port, host, urlPrefix + sUrl,
        responseHandler -> {
          logger.info("Response code: " + responseHandler.statusCode() + " Message: " + responseHandler.statusMessage() + " for request url: " + sUrl);
            responseHandler.bodyHandler(buffer -> {
              logger.info("Buffer for : " + sUrl + " value: " + buffer.toString());
              if (responseHandler.statusCode() == 201) {
                handler.handle(Future.succeededFuture());
              } else {
                if (buffer.length() == 0) {
                  handler.handle(Future.failedFuture(responseHandler.statusMessage()));
                }
                else {
                  handler.handle(Future.failedFuture(buffer.toString()));
                }
              }
            });
        })
      .putHeader("content-type", "application/json")
      .putHeader("accept", "application/json")
      .putHeader("Authorization", "Basic " + encodedCredential)
      .end();
  }

  @Override
  public void taskActivate(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/activated", handler);
  }

  @Override
  public void taskClaim(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/claimed", handler);
  }

  @Override
  public void taskStart(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/started", handler);
  }

  @Override
  public void taskStop(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/stopped", handler);
  }

  @Override
  public void taskComplete(String encodedCredential, String containerName, Long taskId, JsonObject variables, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/completed", handler);
  }

  @Override
  public void taskDelegate(String encodedCredential, String containerName, Long taskId, String delegateId, Handler<AsyncResult<Void>> handler) {

    String sUrl = "server/containers/" + containerName + "/tasks/" + taskId + "/states/delegated?targetUser=" + delegateId;
    processAction(encodedCredential, sUrl, handler);
  }

  @Override
  public void taskExit(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/exited", handler);
  }

  @Override
  public void taskFail(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/failed", handler);
  }

  @Override
  public void taskForward(String encodedCredential, String containerName, Long taskId, String userId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/forwarded?targetUser=" + userId, handler);
  }

  @Override
  public void taskRelease(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/released", handler);
  }

  @Override
  public void taskResume(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/resumed", handler);
  }

  @Override
  public void taskSkip(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/skipped", handler);
  }

  @Override
  public void taskSuspend(String encodedCredential, String containerName, Long taskId, Handler<AsyncResult<Void>> handler) {

    processAction(encodedCredential, "server/containers/" + containerName + "/tasks/" + taskId + "/states/suspended", handler);
  }

  @Override
  public void taskNominate(String encodedCredential, String containerName, Long taskId, List<String> nominations, Handler<AsyncResult<Void>> handler) {

    StringJoiner sj = new StringJoiner("&potOwner=", "potOwner=","");
    nominations.forEach(sj::add);

    String sUrl = "server/containers/" + containerName + "/tasks/" + taskId + "/states/nominated?" + sj.toString();
    processAction(encodedCredential, sUrl, handler);
  }


}
